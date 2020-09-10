package at.fh.hagenberg.aist.jack.reflection;

import at.fh.hagenberg.aist.jack.data.Pair;
import at.fh.hagenberg.aist.jack.exception.ExceptionUtils;
import at.fh.hagenberg.aist.jack.general.transformer.ForwardTransformer;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>Created by Andreas Pointner on 17.03.2020</p>
 * <p>Transformer to transform an kind of object into a property map</p>
 *
 * @author Andreas Pointner
 */
@Setter
public class ObjectToPropertyMapTransformer implements ForwardTransformer<Object, Map<String, Object>> {

    /**
     * If the fields of the parent objects should be included as well
     */
    private boolean includeParentFields = false;

    /**
     * if synthetic fields should be included as well. Note: this will cause jenkins to get different results, as
     * sonar adds some synthetic fields
     */
    private boolean includeSyntheticFields = false;

    /**
     * Returns a Property Map for the given object, where the key is the name of the field and the value the value of the field
     *
     * @param o the object
     * @return a property map
     */
    @Override
    public Map<String, Object> transformFrom(Object o) {
        Class<?> clazz = o.getClass();
        Stream<Field> fieldsForClass = includeParentFields ? ReflectionUtils.getAllFieldsOfClass(clazz).stream() : Arrays.stream(clazz.getDeclaredFields());
        if (!includeSyntheticFields) fieldsForClass = fieldsForClass.filter(f -> !f.isSynthetic());

        return fieldsForClass.map(ExceptionUtils.uncheck(f -> {
            boolean access = f.canAccess(o);
            try {
                f.setAccessible(true);
                return Pair.of(f.getName(), f.get(o));
            } finally {
                f.setAccessible(access);
            }
        }))
                .collect(Pair.toMap());
    }
}
