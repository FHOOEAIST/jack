package at.fh.hagenberg.aist.jack.reflection;

import at.fh.hagenberg.aist.jack.data.Pair;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Created by Christoph Praschl on 12/03/2019</p>
 * <p>Class containing utility functionality for reflection based programming with primitive and boxing types</p>
 *
 * @author Christoph Praschl
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Autoboxers {

    /**
     * Map of boxing classes and related unboxed classes
     */
    private static final Map<Class<?>, Class<?>> BOXING_CLASSES;
    /**
     * Map of boxing classes and related unboxed classes
     */
    private static final Map<Class<?>, Class<?>> PRIMITIVE_CLASSES;
    /**
     * Map containing the default values of primitive data types
     */
    private static final Map<Class<?>, Object> DEFAULT_VALUES;

    static {
        List<Pair<? extends Class<? extends Serializable>, ? extends Class<? extends Serializable>>> collect = Stream.of(
                Pair.of(Boolean.class, boolean.class),
                Pair.of(Byte.class, byte.class),
                Pair.of(Character.class, char.class),
                Pair.of(Double.class, double.class),
                Pair.of(Float.class, float.class),
                Pair.of(Integer.class, int.class),
                Pair.of(Long.class, long.class),
                Pair.of(Short.class, short.class)).collect(Collectors.toList());

        BOXING_CLASSES = collect.stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
        PRIMITIVE_CLASSES = collect.stream().collect(Collectors.toMap(Pair::getSecond, Pair::getFirst));
        DEFAULT_VALUES = BOXING_CLASSES.values().stream()
                .collect(Collectors.toMap(Function.identity(),clazz -> Array.get(Array.newInstance(clazz, 1), 0)));

    }

    /**
     * @return a map of boxing classes (key) with the corresponding primitive classes (value)
     */
    public static Map<Class<?>, Class<?>> getBoxingClasses() {
        return Collections.unmodifiableMap(BOXING_CLASSES);
    }

    /**
     * @return the default values of boxing classes
     */
    public static Map<Class<?>, Object> getPrimitiveDefaultValues() {
        return Collections.unmodifiableMap(DEFAULT_VALUES);
    }

    /**
     * @param clazz for which default value is needed
     * @param <T> type parameter
     * @return default value of the given class or NULL if given clazz is not primitive
     */
    @SuppressWarnings("unchecked")
    public static <T> T getPrimitiveDefaultValue(Class<T> clazz) {
        return (T) Collections.unmodifiableMap(DEFAULT_VALUES).get(clazz);
    }

    /**
     * @return a map of primitive classes (key) with the corresponding boxing classes (value)
     */
    public static Map<Class<?>, Class<?>> getPrimitiveClasses() {
        return Collections.unmodifiableMap(PRIMITIVE_CLASSES);
    }

    /**
     * Checks if given clazz is a boxing clazz
     * @param clazz clazz to check
     * @return true if clazz is a boxing class else false
     */
    public static boolean isBoxingClass(Class<?> clazz){
        return BOXING_CLASSES.containsKey(clazz);
    }

    /**
     * Checks if given clazz is a primitive clazz
     * @param clazz clazz to check
     * @return true if clazz is a primitive class else false
     */
    public static boolean isPrimitiveClass(Class<?> clazz){
        return PRIMITIVE_CLASSES.containsKey(clazz);
    }

    /**
     * Checks if given clazz is a primitive clazz or a boxing clazz
     * @param clazz clazz to check
     * @return true if clazz is a primitive or a boxing class else false
     */
    public static boolean isPrimitiveOrBoxingClass(Class<?> clazz){
        return isBoxingClass(clazz) || isPrimitiveClass(clazz);
    }

    /**
     * Returns the associated boxing type for a given primitive class
     * @param primitiveClass for which boxing type is required
     * @return boxing type
     * @throws IllegalArgumentException if given class is not a primitive type
     */
    public static Class<?> getBoxingClass(Class<?> primitiveClass){
        if(!isPrimitiveClass(primitiveClass)){
            throw new IllegalArgumentException("Given class is not a primitive class");
        }

        return getPrimitiveClasses().get(primitiveClass);
    }

    /**
     * Returns the associated primitive type for a given boxing class
     * @param boxingClass for which primitive type is required
     * @return primitive type
     * @throws IllegalArgumentException if given class is not a boxing type
     */
    public static Class<?> getPrimitiveClass(Class<?> boxingClass){
        if(!isBoxingClass(boxingClass)){
            throw new IllegalArgumentException("Given class is not a boxing class");
        }

        return getBoxingClasses().get(boxingClass);
    }
}