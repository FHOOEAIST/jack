/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.general.transformer;

import java.util.Collection;

/**
 * <p>Generic interface for a reversed unidirectional Transformer between a collection of a class to collection of another</p>
 *
 * @param <FROM> TO which should be transformed to FROM
 * @param <TO>   FROM which is transformed from TO
 * @author Christoph Praschl
 * @since 2.0
 */
@FunctionalInterface
@SuppressWarnings("java:S119") // Naming of type arguments
public interface BackwardCollectionTransformer<FROM, TO> {
    /**
     * Transforms a Collection of TO to a Collection of FROM
     *
     * @param collection the collection to be transformed
     * @return the resulting collection
     */
    Collection<FROM> transformToCollection(Collection<TO> collection);
}
