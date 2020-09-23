/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.general.transformer;

/**
 * <p>Generic interface for a unidirectional Transformer between a class to another</p>
 *
 * @param <FROM> FROM which should be transformed to TO
 * @param <TO>   TO which is transformed from FROM
 * @author Christoph Praschl
 * @since 2.0
 */
@FunctionalInterface
@SuppressWarnings("java:S119") // Naming of type arguments
public interface ForwardTransformer<FROM, TO> {
    /**
     * Transform FROM object to a TO object
     *
     * @param from object to be transformed
     * @return transformed type
     */
    TO transformFrom(FROM from);
}
