package at.fh.hagenberg.aist.jack.persistence.filesystem;

import at.fh.hagenberg.aist.jack.persistence.filesystem.implementation.GenericFileStorage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Christoph Praschl on 14.06.2017.
 *
 * @author Christoph Praschl p41743@fh-hagenberg.at
 * Description: Test class for testing TextfileStorage
 */
public class TextfileStorageTest extends AbstractFileStorageTest {
    private GenericFileStorage<String> textfileStorage = new GenericFileStorage<>(String.class, "text", ".txt");

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
