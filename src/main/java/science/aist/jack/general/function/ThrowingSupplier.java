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
 * <p>Implementation of {@link java.util.function.Supplier} to allow also exceptions</p>
 *
 * @param <T> the supplied result type
 * @author Andreas Pointner
 * @since 2.0
 */
@FunctionalInterface
public interface ThrowingSupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Exception in case of any error
     */
    @SuppressWarnings("java:S112")
    // Exception is exactly what this should be declaring to allow to use it for all sorts of exceptions
    T get() throws Exception;
}
