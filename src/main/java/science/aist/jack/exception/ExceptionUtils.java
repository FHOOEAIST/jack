/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.exception;

import lombok.SneakyThrows;
import science.aist.jack.general.function.ThrowingFunction;
import science.aist.jack.general.function.ThrowingSupplier;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>Util class for Exception handling</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public final class ExceptionUtils {
    private ExceptionUtils() {
    }

    /**
     * Magic method, to avoid the java compile exception check
     *
     * @param t   the exception to be thrown
     * @param <T> the type of the exception
     * @throws T throw the exception in an uncheck way
     */
    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwIt(Throwable t) throws T {
        throw (T) t;
    }

    /**
     * <p>Do a Static import of this function, and the use it to throw uncheck checked exception, by using e.g.:</p>
     * <pre>
     *   throw unchecked(new IOException());
     * </pre>
     * <p>Why would you ever want to wrap a checked exception with an unchecked one, when you can use this hack. e.g.</p>
     * <p>Instead of:</p>
     * <pre>
     *   try {
     *     doSomething();
     *   } catch (IOException ioe) {
     *     throw new RuntimeException(ioe);
     *   }
     * </pre>
     * <p>you can directly use: </p>
     * <pre>
     *   try {
     *     doSomething();
     *   } catch (IOException ioe) {
     *     throw ExceptionUtils.unchecked(ioe); // this will throw IOException directly
     *   }
     * </pre>
     *
     * @param t the exception to be thrown
     * @return a runtimeException to avoid compiler checks
     */
    public static RuntimeException unchecked(Throwable t) {
        throwIt(t);
        // This can never happen, as the throwIt function will definitely throw an exception.
        // Just added to satisfy the compiler
        throw new IllegalStateException();
    }

    /**
     * <p>Do a Static import of this function, and the use it to throw uncheck checked exception, by using e.g.:</p>
     * <pre>
     *   return failure(new IOException());
     * </pre>
     * <p>Can also be used to fail a method with a check exception without the need to declare it to the method:</p>
     * <pre>
     *   failure(new IOException());
     * </pre>
     *
     * @param t   the exception to be thrown
     * @param <T> any type, to satisfy the return type
     * @return nothing, as always an exception will be thrown
     */
    public static <T> T failure(Throwable t) {
        throw unchecked(t);
    }

    /**
     * <p>Allows the usage of a lambda expression that would throw an checked exception, and converts that into a {@link Function}
     * that throws an uncheck function</p>
     *
     * <pre>
     *     .stream()
     *     .map(ExceptionUtils.uncheck(val -&gt; val.iThrowAnException()));
     * </pre>
     *
     * @param throwingFunction the function that should be executing declaring a checked exception
     * @param <T>              input type
     * @param <R>              output type
     * @return a function that converts the checked function into a unchecked one
     */
    @SuppressWarnings("squid:S1604")
    public static <T, R> Function<T, R> uncheck(ThrowingFunction<T, R> throwingFunction) {
        //noinspection Convert2Lambda,Anonymous2MethodRef - not possible because annotation is needed to method
        return new Function<>() {
            @Override
            @SneakyThrows
            public R apply(T t) {
                return throwingFunction.apply(t);
            }
        };
    }

    /**
     * <p>Allows the usage of a lambda expression that would throw an checked exception, and converts that into a {@link Supplier}</p>
     * <pre>
     *     Optional
     *      .orElseGet(ExceptionUtils.uncheck(() -&gt; someMethodCall.thatThrowsException()));
     * </pre>
     *
     * @param throwingSupplier the supplier that should be executed declaring a checked exception
     * @param <T>              the result type
     * @return a supplier that converts the checked supplier into a unchecked one
     */
    @SuppressWarnings("squid:S1604")
    public static <T> Supplier<T> uncheck(ThrowingSupplier<T> throwingSupplier) {
        //noinspection Convert2Lambda,Anonymous2MethodRef - not possible because annotation is needed to method
        return new Supplier<>() {
            @Override
            @SneakyThrows
            public T get() {
                return throwingSupplier.get();
            }
        };
    }
}
