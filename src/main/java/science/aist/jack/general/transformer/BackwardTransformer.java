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
 * <p>Generic interface for a reversed unidirectional Transformer between a class to another</p>
 *
 * @param <FROM> TO which should be transformed to FROM
 * @param <TO>   FROM which is transformed from TO
 * @author Christoph Praschl
 * @since 2.0
 */
@FunctionalInterface
@SuppressWarnings("java:S119") // Naming of type arguments
public interface BackwardTransformer<FROM, TO> {
    /**
     * Transform TO object to a FROM object
     *
     * @param to object to be transformed
     * @return transformed type
     */
    FROM transformTo(TO to);
}
