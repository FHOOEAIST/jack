package science.aist.jack.persistence.core;

import java.util.Collection;

/**
 * <p>Created by Christoph Praschl on 14.06.2017.</p>
 * <p>Storage Interface for storing objects</p>
 *
 * @param <K> DataType of key which is used to save value
 * @param <V> DataType of objects which should be stored
 * @author Christoph Praschl
 */


public interface Storage<K, V> {
    /**
     * Method for storing an given object
     *
     * @param value Object which should be stored
     * @return The key of the stored object
     */
    K create(V value);

    /**
     * Method for storing an given object
     *
     * @param value        Object which should be stored
     * @param suggestedKey Suggested Key for storing the object (does not have to be considered! check return value)
     * @return The key of the stored object
     */
    K create(V value, K suggestedKey);

    /**
     * Method for reading an object identified by the given key
     *
     * @param key Key to identify the object which should be read
     * @return The Object identified by the key or NULL if no object is identified by the given key.
     */
    V read(K key);

    /**
     * Method for reading all objects managed by the storage
     *
     * @return Collection of all managed objects
     */
    Collection<V> read();

    /**
     * Method for updating the stored object at a given key
     *
     * @param key   Key to identify the object which should be updated
     * @param value Object which should be restored
     * @return True if object was updated; else false
     */
    boolean update(K key, V value);

    /**
     * Method for deleting a stored object
     *
     * @param key Key to identify the object which should be deleted
     * @return True if object was deleted; else false
     */
    boolean delete(K key);

    /**
     * Method for deleting all stored objects
     * Attention: If corresponding folder is used by multiple users/application this method does not take response for
     * them! Only use it if the folder is only touched by this
     * {@link Storage}
     *
     * @return True if object was deleted; else false
     */
    boolean deleteAll();
}
