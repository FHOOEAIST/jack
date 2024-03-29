/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.persistence.filesystem;

import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.jack.persistence.filesystem.implementation.GenericFileStorage;

/**
 * <p>Test class for testing TextfileStorage</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
public class TextfileStorageTest extends AbstractFileStorageTest {
    private final GenericFileStorage<String> textfileStorage = new GenericFileStorage<>(String.class, "text", ".txt");

    @Test
    public void testCreate1() {
        // given
        String content = "adfadf adfadfa dfadfa";

        // when
        String key = textfileStorage.create(content);

        // then
        checkCreationAndCleanup(textfileStorage, key);

    }

    @Test
    public void testCreate2() {
        // given
        String filename = "test";
        String content = "adfadf adfadfa dfadfa";

        // when
        String key = textfileStorage.create(content, filename);

        // then
        Assert.assertEquals(filename, key);
        checkCreationAndCleanup(textfileStorage, key);
    }


    @Test
    public void testUpdate() {
        // given
        String content = "adfadf adfadfa dfadfa";
        String newContent = "new";
        String key = textfileStorage.create(content);

        // when
        boolean success = textfileStorage.update(key, newContent);

        // then
        Assert.assertTrue(success);
        checkCreationAndCleanup(textfileStorage, key);
    }

    @Test
    public void testDelete() {
        // given
        String content = "adfadf adfadfa dfadfa";
        String key = textfileStorage.create(content);

        // when
        boolean success = textfileStorage.delete(key);

        // then
        Assert.assertTrue(success);
    }

    @Test
    public void testRead() {
        // given
        String content = "adfadf adfadfa dfadfa";
        String key = textfileStorage.create(content);

        // when
        String readContent = textfileStorage.read(key);

        // then
        Assert.assertEquals(content, readContent);
        checkCreationAndCleanup(textfileStorage, key);
    }
}
