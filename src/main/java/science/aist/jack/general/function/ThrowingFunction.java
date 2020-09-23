/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.general.function;

/**
 * <p>Implementation of {@link java.util.function.Function} to allow also exceptions</p>
 *
 * @param <R> the result of the function
 * @param <T> the input of the function
 * @author Andreas Pointner
 * @since 2.0
 */
@FunctionalInterface
public interface ThrowingFunction<T, R> {

    /**
     * Applies a function to t
     *
     * @param t the input of the function
     * @return the result of the function
     * @throws Exception the exception if one is thrown
     */
    @SuppressWarnings("java:S112")
    // Exception is exactly what this should be declaring to allow to use it for all sorts of exceptions
    R apply(T t) throws Exception;
}
