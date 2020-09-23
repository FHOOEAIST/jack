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
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * <p>Test class for {@link FileUtils}</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
@CustomLog
public class FileUtilsTest {
    @Test
    public void testWriteToTempFile() {
        // given
        try (InputStream resourceAsStream = FileUtilsTest.class.getResourceAsStream("/Test.txt")) {

            // when
            File file = FileUtils.writeToTempFile("test", ".txt", resourceAsStream);

            // then
            Assert.assertTrue(file.exists());
            Files.delete(file.toPath());
            Assert.assertFalse(file.exists());
        } catch (IOException e) {
            log.debug("InputStream could not be closed", e);
        }
    }

    @Test
    public void testWriteToTempFile1() {
        // given
        try (InputStream resourceAsStream = FileUtilsTest.class.getResourceAsStream("/Test.txt")) {
            String dirPath = FileUtils.toCompliantPath("at/fh/hagenberg/aist/");

            // when
            File file = FileUtils.writeToTempFile(dirPath, "test", ".txt", resourceAsStream);

            // then
            Assert.assertTrue(file.exists());
            Files.delete(file.toPath());
            Assert.assertFalse(file.exists());
        } catch (IOException e) {
            log.debug("InputStream could not be closed", e);
        }
    }

    @Test
    public void testCreateTempDirectory() throws IOException {
        // given
        String dirPath = FileUtils.toCompliantPath("at/fh/hagenberg/aist/");


        // when
        Path tempDirectory = FileUtils.createTempDirectory(dirPath);

        // then
        Assert.assertTrue(tempDirectory.toFile().exists());
        Assert.assertTrue(tempDirectory.toFile().isDirectory());
        Files.delete(tempDirectory);
    }

    @Test
    public void testCreateTempFile() throws IOException {
        // given
        String dirPath = FileUtils.toCompliantPath("at/fh/hagenberg/aist/");

        // when
        Path tempFile = FileUtils.createTempFile(dirPath, "test", ".txt");

        // then
        System.out.println(tempFile.toFile().getAbsolutePath());
        Assert.assertTrue(tempFile.toFile().exists());
        Assert.assertTrue(tempFile.toFile().isFile());
        Files.delete(tempFile);
    }

    @Test
    public void testCreateTempFile2() throws IOException {
        // given

        // when
        Path tempFile = FileUtils.createTempFile("test", ".txt");

        // then
        Assert.assertTrue(tempFile.toFile().exists());
        Assert.assertTrue(tempFile.toFile().isFile());
        Files.delete(tempFile);
    }

    @Test
    public void testToCompliantPath() {
        // given
        String dirPath = "at/fh/hagenberg/aist/";

        // when
        String s = FileUtils.toCompliantPath(dirPath);

        // then
        if (!"/".equals(File.separator)) {
            Assert.assertFalse(s.contains("/"));
        } else {
            Assert.assertEquals(s, dirPath);
        }
    }
}