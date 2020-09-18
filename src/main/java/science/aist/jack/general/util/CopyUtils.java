package science.aist.jack.general.util;

import lombok.CustomLog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>Util class defining functions to create copies of other objects.</p>
 *
 * @author Rainer Meindl
 * @since 1.0
 */
@CustomLog
public final class CopyUtils {
    private CopyUtils() {
    }

    /**
     * This method makes a "deep copy" of any object it is given.
     *
     * @param object object to clone
     * @param <T>    type of the object that should be copied
     * @return deep copy of object
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
