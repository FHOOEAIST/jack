/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.persistence.filesystem;

import lombok.CustomLog;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>Utility class for files</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
@CustomLog
public class FileUtils {
    private FileUtils() {
    }

    /**
     * Help method which creates a compliant path string using {@link File#separator}, which is required by the methods of {@link FileUtils}
     *
     * @param path path which contains slash "/" as file separator
     * @return compliant path string
     */
    public static String toCompliantPath(String path) {
        return path.replace("/", File.separator);
    }

    /**
     * Method which creates a directory in the temp folder based on the given directoryHierarchy (subfolder separated by {@link File#separator})
     *
     * @param directoryHierarchy defining the sub folders' name
     * @return path to the temp directory
     */
    public static Path createTempDirectory(String directoryHierarchy) {
        if (directoryHierarchy.contains(".")) {
            throw new IllegalArgumentException("directory hierarchy must not contain a file name");
        }
        StringBuilder sb = new StringBuilder();
        String tempDir = System.getProperty("java.io.tmpdir");
        sb.append(tempDir);
        // check for linux/windows difference with ending File.separator
        if (!tempDir.endsWith(File.separator)) {
            sb.append(File.separator);
        }

        // create temp directory if necessary
        sb.append(directoryHierarchy);
        // finish the path with a File.separator if not given
        if (!directoryHierarchy.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        File folder = new File(sb.toString());
        if (!folder.exists() && !folder.mkdirs()) {
            log.debug("Could not create folder " + sb.toString());
            throw new UncheckedIOException("Could not create folder " + sb.toString(), new IOException());
        }

        return folder.toPath();
    }

    /**
     * Creates a file in the temp folder
     *
     * @param fileName      name of the file which should be created
     * @param fileExtension file extension of the file which should be created
     * @return the created file
     */
    public static Path createTempFile(String fileName, String fileExtension) {
        return createTempFile(null, fileName, fileExtension);
    }

    /**
     * Creates a file in a subfolder (respectively directory hierarchy) of the temp folder
     *
     * @param dirPath       the path of the subfolder
     * @param fileName      name of the file which should be created
     * @param fileExtension file extension of the file which should be created
     * @return the created file
     */
    public static Path createTempFile(String dirPath, String fileName, String fileExtension) {
        return writeToTempFile(dirPath, fileName, fileExtension, null).toPath();
    }

    /**
     * Writes the given inputStream to a file (given by the file name and file extension) in the local TEMP directory
     *
     * @param fileName      name of the file which should be created
     * @param fileExtension file extension of the file which should be created
     * @param is            InputStream which should be written to a new file in the TEMP directory (is not closed inside of this method!); if null nothing is written to the created file
     * @return the created file
     */
    public static File writeToTempFile(String fileName, String fileExtension, InputStream is) {
        return writeToTempFile(null, fileName, fileExtension, is);
    }

    /**
     * Writes the given inputStream to a file (given by the file name and file extension) into a subfolder of the local TEMP directory
     *
     * @param dirPath       Path of the subfolder in the local TEMP directory (may be null; if not use {@link File#separator} for separating different subfolders!)
     * @param fileName      name of the file which should be created
     * @param fileExtension file extension of the file which should be created
     * @param is            InputStream which should be written to a new file in the TEMP directory (is not closed inside of this method!); if null nothing is written to the created file
     * @return the created file
     */
    public static File writeToTempFile(String dirPath, String fileName, String fileExtension, InputStream is) {
        if (fileName == null) {
            throw new IllegalArgumentException("File Name must not be null");
        }

        if (fileExtension == null) {
            throw new IllegalArgumentException("File Extension must not be null");
        }

        Path folder;
        // create temp directory if necessary
        if (dirPath != null) {
            folder = createTempDirectory(dirPath);
        } else {
            folder = Paths.get(System.getProperty("java.io.tmpdir"));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(folder.toString());
        sb.append(File.separator);
        sb.append(fileName);
        if (!fileExtension.startsWith(".")) {
            sb.append(".");
        }
        sb.append(fileExtension);

        // create file and copy input into output
        File result = new File(sb.toString());

        if (is != null) {
            try (OutputStream outputStream = new FileOutputStream(result)) {
                copyStream(is, outputStream);
            } catch (IOException e) {
                log.error("Could not copy data to file " + sb.toString(), e);
                throw new UncheckedIOException("Could not copy data to file " + sb.toString(), e);
            }
        } else {
            try {
                boolean newFile = result.createNewFile();
                if (!newFile) {
                    throw new IllegalStateException();
                }
            } catch (IOException | IllegalStateException e) {
                log.error("Could not create new file", e);
                throw new UncheckedIOException("Could not create new file" + sb.toString(), new IOException(e));
            }
        }
        return result;
    }

    private static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

}
