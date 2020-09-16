package science.aist.jack.persistence.filesystem;

import at.fh.hagenberg.aist.seshat.Logger;
import org.testng.Assert;
import science.aist.jack.persistence.filesystem.implementation.GenericFileStorage;

import java.io.File;

/**
 * <p>Abstract storagetest class</p>
 *
 * @author Christoph Praschl
 * @since 1.0
 */
public abstract class AbstractFileStorageTest {
    protected final Logger log = Logger.getInstance(getClass());

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
