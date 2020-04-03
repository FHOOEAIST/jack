package at.fh.hagenberg.aist.jack.persistence.filesystem;

import at.fh.hagenberg.aist.seshat.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * <p>Created by Christoph Praschl on 02/10/2019</p>
 * <p>Test class for {@link FileUtils}</p>
 *
 * @author Christoph Praschl christoph.praschl@fh-hagenberg.at
 */
public class FileUtilsTest {
    private static Logger logger = Logger.getInstance(FileUtils.class);

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
            logger.debug("Inputstream could not be closed", e);
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
            logger.debug("Inputstream could not be closed", e);
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