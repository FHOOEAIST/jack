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
import science.aist.jack.exception.ExceptionUtils;
import science.aist.jack.persistence.core.Storage;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.NotDirectoryException;
import java.security.SecureRandom;

/**
 * <p>Abstract Implementation of Storage Interface for storing objects in the TEMP folder of the file system</p>
 *
 * @param <K> DataType of key which is used to save value
 * @param <V> DataType of objects which should be stored
 * @author Christoph Praschl
 * @since 2.0
 */
@CustomLog
public abstract class AbstractFileStorage<K, V> implements Storage<K, V> {
    protected final String pathToTempFolder;
    protected final String completePath;
    protected final String fileExtension;
    private final SecureRandom random = new SecureRandom();

    public AbstractFileStorage(String pathToSubFolder, String fileExtension) {
        this(pathToSubFolder, fileExtension, "aist");
    }

    public AbstractFileStorage(String pathToSubFolder, String fileExtension, String subFolder) {
        String tempFolder = System.getProperty("java.io.tmpdir");
        if (tempFolder.endsWith(File.separator)) {
            pathToTempFolder = tempFolder + subFolder;
        } else {
            pathToTempFolder = tempFolder + File.separator + subFolder;
        }

        completePath = pathToTempFolder + pathToSubFolder;
        File dir = new File(completePath);
        if (!dir.exists() || !dir.isDirectory()) {
            boolean success = dir.mkdirs();
            log.info("Directory Path: " + dir.getPath());
            if (!success) {
                throw ExceptionUtils.unchecked(new NotDirectoryException("Directory could not be created at path (" + completePath + ")"));
            }
        }
        this.fileExtension = fileExtension;
    }

    /**
     * Method for getting the storage path
     *
     * @return Storage path
     */
    public String getPath() {
        return completePath;
    }

    /**
     * Method for getting used file extension of storage
     *
     * @return File extension
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * Method for building path with given filename
     *
     * @param filename  name of file at internal path (without file extension!)
     * @param extension file extension
     * @return Complete path containing the name of the .txt file
     */
    protected String buildPath(String filename, String extension) {
        return completePath + File.separator + filename + extension;
    }

    /**
     * Method for building path with given filename
     *
     * @param filename name of file at internal path (without file extension!)
     * @return Complete path containing the name of the .txt file
     */
    protected String buildPath(String filename) {
        return buildPath(filename, fileExtension);
    }

    /**
     * Method for generating a random string key
     *
     * @return Random string key with 32 letters
     */
    protected String generateStringToken() {
        return new BigInteger(130, random).toString(32);
    }
}
