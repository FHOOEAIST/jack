package science.aist.jack.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Utility class for reflection based stuff</p>
 *
 * @author Christoph Praschl
 * @since 1.0
 */
public class ReflectionUtils {
    private ReflectionUtils() {
    }

    /**
     * Collects all fields of class together with all fields of the super classes
     *
     * @param clazz Class to start with
     * @return list of all fields of the class and it`s parents
     */
    public static List<Field> getAllFieldsOfClass(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, clazz.getDeclaredFields());
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            fields.addAll(getAllFieldsOfClass(superclass));
        }
        return fields;
    }

}