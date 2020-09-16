package science.aist.jack.general.util;

import at.fh.hagenberg.aist.seshat.Logger;

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
public final class CopyUtils {
    private static final Logger logger = Logger.getInstance(CopyUtils.class);

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
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
