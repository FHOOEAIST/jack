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
import science.aist.jack.persistence.filesystem.implementation.GenericFileStorage;

import java.io.File;

/**
 * <p>Abstract storagetest class</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
@CustomLog
public abstract class AbstractFileStorageTest {
    /**
     * Method for checking file creation and for clean up (= delete file)
     *
     * @param storage  file storage used for file creation
     * @param filename Name of file which should has been created
     */
    protected void checkCreationAndCleanup(GenericFileStorage<?> storage, String filename) {
        String filepath = storage.getPath() + File.separator + filename + storage.getFileExtension();
        log.debug("Filepath: " + filepath);
        File file = new File(filepath);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
    }

}
