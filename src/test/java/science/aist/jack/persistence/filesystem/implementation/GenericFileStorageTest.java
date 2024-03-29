/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.persistence.filesystem.implementation;

import lombok.CustomLog;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.jack.persistence.filesystem.AbstractFileStorageTest;
import science.aist.jack.persistence.filesystem.domain.JavaPoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>Test class for GenericFileStorage</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
@CustomLog
public class GenericFileStorageTest extends AbstractFileStorageTest {
    private final GenericFileStorage<JavaPoint> genericStorage = new GenericFileStorage<>(JavaPoint.class, ".javapoint");

    @Test
    public void testCreate1() {
        // given
        JavaPoint jp = new JavaPoint(1, 2);

        // when
        String key = genericStorage.create(jp);

        // then
        log.debug(genericStorage.getPath() + "\\" + key);
        checkCreationAndCleanup(genericStorage, key);
    }

    @Test
    public void testCreate2() {
        // given
        JavaPoint jp = new JavaPoint(1, 2);

        // when
        String key = genericStorage.create(jp, "test");

        // then
        checkCreationAndCleanup(genericStorage, key);
    }

    @Test
    public void testCreate3() {
        // given
        JavaPoint jp = new JavaPoint(1, 2);
        String key = genericStorage.create(jp, "test");

        // when
        String secondKey = genericStorage.create(jp, key);

        // then
        Assert.assertNotEquals(key, secondKey);
        checkCreationAndCleanup(genericStorage, key);
        checkCreationAndCleanup(genericStorage, secondKey);
    }

    @Test
    public void testRead1() {
        // given
        JavaPoint jp = new JavaPoint(1, 2);
        String key = genericStorage.create(jp);

        // when
        JavaPoint readJp = genericStorage.read(key);

        // then
        Assert.assertEquals(jp, readJp);
        checkCreationAndCleanup(genericStorage, key);
    }

    @Test
    public void testRead2() {
        // given

        // when
        JavaPoint readJp = genericStorage.read("test");

        // then
        Assert.assertNull(readJp);
    }

    @Test
    public void testRead3() {
        // given

        // when
        Collection<JavaPoint> readJp = genericStorage.read();

        // then
        Assert.assertEquals(readJp.size(), 0);
    }

    @Test
    public void testRead4() {
        // given
        List<String> list = new ArrayList<>();
        list.add(genericStorage.create(new JavaPoint(1, 2)));
        list.add(genericStorage.create(new JavaPoint(2, 3)));
        list.add(genericStorage.create(new JavaPoint(4, 5)));
        list.add(genericStorage.create(new JavaPoint(6, 7)));

        // when
        Collection<JavaPoint> readJp = genericStorage.read();


        // then
        Assert.assertEquals(list.size(), readJp.size());
        for (String key : list) {
            checkCreationAndCleanup(genericStorage, key);
        }
    }

    @Test
    public void testUpdate1() {
        // given
        JavaPoint jp = new JavaPoint(1, 2);
        String key = genericStorage.create(jp);
        jp = new JavaPoint(10, 2);

        // when
        boolean success = genericStorage.update(key, jp);

        // then
        Assert.assertTrue(success);
        Assert.assertEquals(jp, genericStorage.read(key));
        checkCreationAndCleanup(genericStorage, key);
    }

    @Test
    public void testUpdate2() {
        // given
        JavaPoint jp = new JavaPoint(1, 2);

        // when
        boolean success = genericStorage.update("test", jp);

        // then
        Assert.assertFalse(success);
    }

    @Test
    public void testDelete1() {
        // given
        JavaPoint jp = new JavaPoint(1, 2);
        String key = genericStorage.create(jp);

        // when
        boolean success = genericStorage.delete(key);

        // then
        Assert.assertTrue(success);
    }

    @Test
    public void testDelete2() {
        // given

        // when
        boolean success = genericStorage.delete("test");

        // then
        Assert.assertFalse(success);
    }

    // deleteAllTest priority is necessary so the test is always the last one otherwise the can be concurrency problems
    @Test(priority = 99)
    public void testDeleteAll() {
        // given
        JavaPoint jp = new JavaPoint(3, 4);
        JavaPoint jp2 = new JavaPoint(3, 6);
        JavaPoint jp3 = new JavaPoint(5, 3);
        String key = genericStorage.create(jp);
        String key2 = genericStorage.create(jp2);
        String key3 = genericStorage.create(jp3);

        // when
        boolean success = genericStorage.deleteAll();

        // then
        Assert.assertTrue(success);
        Assert.assertNull(genericStorage.read(key));
        Assert.assertNull(genericStorage.read(key2));
        Assert.assertNull(genericStorage.read(key3));
    }
}
