package at.fh.hagenberg.aist.jack.persistence.filesystem.implementation;

import at.fh.hagenberg.aist.jack.exception.ExceptionUtils;
import at.fh.hagenberg.aist.jack.persistence.filesystem.AbstractFileStorage;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>Created by Christoph Praschl on 14.06.2017.</p>
 * <p>Description: Generic Implementation of the AbstractFileStorage</p>
 *
 * @author Christoph Praschl
 */
@SuppressWarnings("unused")
public class GenericFileStorage<V extends Serializable> extends AbstractFileStorage<String, V> {
    private final Class<V> clazz;

    public GenericFileStorage(Class<V> clazz) {
        this(clazz, ".ser");
    }

    public GenericFileStorage(Class<V> clazz, String fileExtension) {
        this(clazz, fileExtension, File.separator + clazz.getSimpleName());
    }

    public GenericFileStorage(Class<V> clazz, String fileExtension, String subfolder) {
        super(File.separator + subfolder, fileExtension);
        this.clazz = clazz;
    }

    /**
     * Help methods for deleting recursive the content of a folder
     *
     * @param path path to the folder which should be deleted together with its content
     * @throws IOException if something could not be deleted
     */
    private static void deleteDirectoryRecursion(Path path) throws IOException {
        if (path.toFile().isDirectory()) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteDirectoryRecursion(entry);
                }
            }
        }
        Files.delete(path);
    }

    /**
     * Method for storing an given object
     *
     * @param value Object which should be stored
     * @return The key of the stored object
     */
    @Override
    public String create(V value) {
        return create(value, generateStringToken());
    }

    /**
     * Method for storing an given object
     *
     * @param value        Object which should be stored
     * @param suggestedKey Suggested Key for storing the object (does not have to be considered! check return value)
     * @return The key of the stored object
     */
    @Override
    public String create(V value, String suggestedKey) {
        String filePath = buildPath(suggestedKey);
        File file = new File(filePath);

        // check if file already exists
        // if so generate new key and call recursively
        if (file.exists()) {
            return create(value, generateStringToken());
        }

        log.info(file.getPath());

        try (OutputStream os = new FileOutputStream(filePath);
             OutputStream buffer = new BufferedOutputStream(os);
             ObjectOutput output = new ObjectOutputStream(buffer)) {
            output.writeObject(value);
        } catch (IOException ex) {
            throw ExceptionUtils.unchecked(ex);
        }
        return suggestedKey;
    }

    /**
     * Method for reading an file from the given path
     *
     * @param filepath Path to the file which should be read
     * @return Read object
     */
    private V readFileToObject(String filepath) {
        try (InputStream file = new FileInputStream(filepath);
             InputStream buffer = new BufferedInputStream(file);
             ObjectInput input = new ObjectInputStream(buffer)) {
            return clazz.cast(input.readObject());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Method for reading an object identified by the given key
     *
     * @param key Key to identify the object which should be read
     * @return The Object identified by the key or NULL if no object is identified by the given key.
     */
    @Override
    public V read(String key) {
        return readFileToObject(buildPath(key));
    }

    /**
     * Method for reading all objects managed by the storage
     *
     * @return Collection of all managed objects
     */
    public Collection<V> read() {
        List<V> l = new ArrayList<>();
        try {
            try (Stream<Path> paths = Files.walk(Paths.get(completePath))) {
                paths.filter(Files::isRegularFile).forEach(path -> {
                    V value = readFileToObject(path.toString());
                    l.add(value);
                });
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return l;
        }
        return l;
    }

    /**
     * Method for updating the stored object at a given key
     *
     * @param key   Key to identify the object which should be updated
     * @param value Object which should be restored
     * @return True if object was updated; else false
     */
    @Override
    public boolean update(String key, V value) {
        String path = buildPath(key);
        File file = new File(path);
        if (!file.exists()) return false;

        try (OutputStream os = new FileOutputStream(file);
             OutputStream buffer = new BufferedOutputStream(os);
             ObjectOutput output = new ObjectOutputStream(buffer)) {
            output.writeObject(value);
            return true;
        } catch (IOException ex) {
            throw ExceptionUtils.unchecked(ex);
        }
    }

    /**
     * Method for deleting a stored object
     *
     * @param key Key to identify the object which should be deleted
     * @return True if object was deleted; else false
     */
    @Override
    @SuppressWarnings("java:S4042") // Using Files.delete instead of file.delete ...
    public boolean delete(String key) {
        File file = new File(buildPath(key));
        return file.exists() && file.delete();
    }

    /**
     * Method for deleting all stored objects
     * Attention: If corresponding folder is used by multiple users/application this method does not take response for
     * them! Only use it if the folder is only touched by this
     * {@link at.fh.hagenberg.aist.jack.persistence.core.Storage}
     *
     * @return True if object was deleted; else false
     */
    @Override
    public boolean deleteAll() {
        try {
            deleteDirectoryRecursion(Paths.get(completePath));
            return true;
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
            return false;
        }
    }

}