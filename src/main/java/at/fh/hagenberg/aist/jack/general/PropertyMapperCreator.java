package at.fh.hagenberg.aist.jack.general;

import at.fh.hagenberg.aist.jack.general.function.TriConsumer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * <p>Created by Andreas Pointner on 03.12.2018</p>
 * <p>This class creates a PropertyMapper, that is based on the ideas of Andreas Schuler PropertyMapper from the archetype</p>
 * <p>The property mapper creator is able to define mappings from one class into another class.
 * Therefore different mappings can be defined, then the actual PropertyMapper can be created using {@link PropertyMapperCreator#create()}
 * method, which is going to create a Function, which then contains the mapping between those to elements.</p>
 * <br>
 * Example:
 * <pre>
 *   // Both classes have getters and setters for the properties.
 *   class Person {
 *     String firstName;
 *     String lastName;
 *     LocalDate dateOfBirth;
 *   }
 *
 *   class PersonDTO {
 *     String firstName;
 *     String lastName;
 *     String dateOfBirth;
 *   }
 *
 *   // Mapping from a Person to a PersonDTO
 *   Function&lt;Person, PersonDTO&gt; propertyMapper =
 *             new PropertyMapperCreator&lt;Person, PersonDTO&gt;()
 *                     .from(Person::getFirstName).to(PersonDTO::setFirstName) // defines a normal mapping, if types are matching
 *                     .from(Person::getLastName).to(PersonDTO::setLastName) // defines a normal mapping, if types are matching
 *                     .from(Person::getDateOfBirth).toWith(PersonDTO::setDateOfBirth).with(ld -&gt; ld.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))) // defines a complexer mapping with a mapping function (if types are not matching or additional transformation is required)
 *                     .create(PersonDTO::new);
 * </pre>
 *
 * @param <From> The source class for the mapping
 * @param <To>   The target class for the mapping
 * @author Andreas Pointner
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings({"java:S119", "unused"}) // Naming of type arguments and ignoring unused methods
public class PropertyMapperCreator<From, To> {

    /**
     * A list which contains the mappings for the different properties
     */
    private final List<BiConsumer<From, To>> mappings;

    /**
     * Creates a new empty property mapper creator and initializes all internal fields.
     */
    public PropertyMapperCreator() {
        mappings = new ArrayList<>();
    }

    /**
     * <p>Creates the PropertyMapper</p>
     *
     * @param elementCreator The Supplier for the To element, which is used to create the element before the mapping
     * @return returns the property mapper as function
     */
    public Function<From, To> create(Supplier<To> elementCreator) {
        return from -> {
            To to = elementCreator.get();
            mappings.forEach(c -> c.accept(from, to));
            return to;
        };
    }

    /**
     * <p>Creates the PropertyMapper</p>
     *
     * @return returns the property mapper as bi function, where the to element is the second parameter in the function. The function result, then returns the to element after mapping.
     */
    public BiFunction<From, To, To> create() {
        return (from, to) -> {
            mappings.forEach(c -> c.accept(from, to));
            return to;
        };
    }

    //region 1:1 mapping - typesafe

    /**
     * <p>From which property the mapping shell start</p>
     * <br>
     * Example:
     * <pre>
     *  .from(Person::getFirstName)
     * </pre>
     *
     * @param from The from mapping function
     * @param <T>  the type of the property
     * @return the mapping creator for the to mapping
     */
    public <T> PropertyMapperCreatorTo<T> from(Function<From, T> from) {
        return new PropertyMapperCreatorTo<>(from, a -> true);
    }

    /**
     * <p>From which property the mapping shell start</p>
     * <br>
     * Example:
     * <pre>
     *  .fromIf(Person::getFirstName)
     * </pre>
     *
     * @param from The from mapping function
     * @param <T>  the type of the property
     * @return the mapping creator for the to mapping
     */
    public <T> PropertyMapperCreatorFromIf<T> fromIf(Function<From, T> from) {
        return new PropertyMapperCreatorFromIf<>(from);
    }

    /**
     * <p>Defines two from mappings, that can then be mapped to a single to mapping</p>
     * <pre>
     *     propertyMapperCreator
     *       .from(Person::getFirstName, Person::getLastName);
     * </pre>
     *
     * @param from1 the first from mapping
     * @param from2 the second from mapping
     * @param <T1>  the type of the first mapping
     * @param <T2>  the type of the second mapping
     * @return a property mapper creator to
     */
    public <T1, T2> PropertyMapperCreatorTo2<T1, T2> from(Function<From, T1> from1, Function<From, T2> from2) {
        return new PropertyMapperCreatorTo2<>(from1, from2, (x, y) -> true);
    }

    /**
     * <p>Defines two from mappings, that can then be mapped to a single to mapping</p>
     * <pre>
     *     propertyMapperCreator
     *       .fromIff(Person::getFirstName, Person::getLastName);
     * </pre>
     *
     * @param from1 the first from mapping
     * @param from2 the second from mapping
     * @param <T1>  the type of the first mapping
     * @param <T2>  the type of the second mapping
     * @return a property mapper creator from if
     */
    public <T1, T2> PropertyMapperCreatorFromIf2<T1, T2> fromIf(Function<From, T1> from1, Function<From, T2> from2) {
        return new PropertyMapperCreatorFromIf2<>(from1, from2);
    }
    //endregion

    //region 2:1 mapping - typesafe

    //region n:1 mapping - unsafe
    @SafeVarargs
    public final PropertyMapperCreatorToN from(Function<From, ?>... from) {
        return new PropertyMapperCreatorToN(Arrays.asList(from), x -> true);
    }

    @SafeVarargs
    public final PropertyMapperCreatorFromIfN fromIf(Function<From, ?>... from) {
        return new PropertyMapperCreatorFromIfN(Arrays.asList(from));
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class PropertyMapperCreatorFromIf<T> {
        /**
         * The function which create the source object.
         */
        private final Function<From, T> from;

        /**
         * iff --&gt; if and only if. the problem is that if is a keyword, therefore we ues iff
         * <br>
         * Example:
         * <pre>
         *   .iff(Objects::nonNull)
         * </pre>
         *
         * @param predicate the predicate to be check before the mapping
         * @return the to mapping
         */
        public PropertyMapperCreatorTo<T> iff(Predicate<T> predicate) {
            return new PropertyMapperCreatorTo<>(from, predicate);
        }
    }

    /**
     * The inner class which is used to create the to-mapping.
     *
     * @param <T> the property type of the source
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class PropertyMapperCreatorTo<T> {
        /**
         * The function which create the source object.
         */
        private final Function<From, T> from;

        /**
         * The predicate to be check if the mapping should be done
         */
        private final Predicate<T> predicate;

        /**
         * <p>Creates the to mapping with an additional mapping function</p>
         * <br>
         * Example:
         * <pre>
         *   .to(PersonDTO::setFirstName)
         * </pre>
         *
         * @param to  to which property it should be mapped
         * @param <S> the type to which it should be mapped
         * @return the mapping creator for the with mapping
         */
        public <S> PropertyMapperCreatorWith<S> toWith(BiConsumer<To, S> to) {
            return new PropertyMapperCreatorWith<>(from, to, predicate);
        }

        /**
         * <p>Creates the to mapping</p>
         * <br>
         * Example:
         * <pre>
         *   .to(PersonDTO::setFirstName)
         * </pre>
         *
         * @param to to which property it should be mapped
         * @return the PropertyMapper itself.
         */
        public PropertyMapperCreator<From, To> to(BiConsumer<To, T> to) {
            return new PropertyMapperCreatorWith<>(from, to, predicate).with(Function.identity());
        }

        /**
         * The inner class which is used to create the with-mapping
         *
         * @param <S> the property type of the target
         */
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        public class PropertyMapperCreatorWith<S> {
            /**
             * The function which creates the source object
             */
            private final Function<From, T> from;

            /**
             * The consumer which stores the sources object into the target object
             */
            private final BiConsumer<To, S> to;

            /**
             * The predicate to be check if the mapping should be done
             */
            private final Predicate<T> predicate;

            /**
             * <p>defines a function, of how the source property should be mapped to the target one</p>
             * <br>
             * Example:
             * <pre>
             *   .with(String::toLowerCase)
             * </pre>
             *
             * @param mapping defines a function, of how the source property should be mapped to the target one
             * @return the PropertyMapper itself.
             */
            public PropertyMapperCreator<From, To> with(Function<T, S> mapping) {
                mappings.add((input, output) -> {
                    T apply = from.apply(input);
                    if (predicate.test(apply)) {
                        to.accept(output, mapping.apply(apply));
                    }
                });
                return PropertyMapperCreator.this;
            }
        }
    }
    //endregion

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class PropertyMapperCreatorFromIf2<T1, T2> {
        private final Function<From, T1> from1;
        private final Function<From, T2> from2;

        /**
         * <p>Adds a predicate that checks if further execution of the mapping is necessary</p>
         * <pre>
         *     fromMapping
         *       .iff((firstName, lastName) -&gt; !(firstName.empty || lastName.empty));
         * </pre>
         *
         * @param predicate the predicate
         * @return a property mapper creator to
         */
        public PropertyMapperCreatorTo2<T1, T2> iff(BiPredicate<T1, T2> predicate) {
            return new PropertyMapperCreatorTo2<>(from1, from2, predicate);
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class PropertyMapperCreatorTo2<T1, T2> {
        private final Function<From, T1> from1;
        private final Function<From, T2> from2;
        private final BiPredicate<T1, T2> predicate;

        public PropertyMapperCreator<From, To> to(TriConsumer<To, T1, T2> to) {
            mappings.add((input, output) -> {
                T1 apply1 = from1.apply(input);
                T2 apply2 = from2.apply(input);

                if (predicate.test(apply1, apply2)) {
                    to.accept(output, apply1, apply2);
                }
            });
            return PropertyMapperCreator.this;
        }

        public <S> PropertyMapperCreatorWith<S> toWith(BiConsumer<To, S> to) {
            return new PropertyMapperCreatorWith<>(from1, from2, to, predicate);
        }

        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        public class PropertyMapperCreatorWith<S> {
            private final Function<From, T1> from1;

            private final Function<From, T2> from2;

            private final BiConsumer<To, S> to;

            private final BiPredicate<T1, T2> predicate;

            public PropertyMapperCreator<From, To> with(BiFunction<T1, T2, S> mapping) {
                mappings.add((input, output) -> {
                    T1 apply1 = from1.apply(input);
                    T2 apply2 = from2.apply(input);
                    if (predicate.test(apply1, apply2)) {
                        to.accept(output, mapping.apply(apply1, apply2));
                    }
                });
                return PropertyMapperCreator.this;
            }
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class PropertyMapperCreatorFromIfN {
        private final List<Function<From, ?>> from;

        public PropertyMapperCreatorToN iff(Predicate<List<?>> predicate) {
            return new PropertyMapperCreatorToN(from, predicate);
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public class PropertyMapperCreatorToN {
        private final List<Function<From, ?>> from;
        private final Predicate<List<?>> predicate;

        public PropertyMapperCreator<From, To> to(BiConsumer<To, List<?>> to) {
            mappings.add((input, output) -> {
                List<?> result = from.stream()
                        .map(f -> f.apply(input))
                        .collect(Collectors.toList());

                if (predicate.test(result)) {
                    to.accept(output, result);
                }
            });
            return PropertyMapperCreator.this;
        }

        public <S> PropertyMapperCreatorWith<S> toWith(BiConsumer<To, S> to) {
            return new PropertyMapperCreatorWith<>(from, to, predicate);
        }

        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        public class PropertyMapperCreatorWith<S> {
            private final List<Function<From, ?>> from;
            private final BiConsumer<To, S> to;
            private final Predicate<List<?>> predicate;

            public PropertyMapperCreator<From, To> with(Function<List<?>, S> mapping) {
                mappings.add((input, output) -> {
                    List<?> result = from.stream()
                            .map(f -> f.apply(input))
                            .collect(Collectors.toList());

                    if (predicate.test(result)) {
                        to.accept(output, mapping.apply(result));
                    }
                });
                return PropertyMapperCreator.this;
            }
        }
    }
    //endregion


}
