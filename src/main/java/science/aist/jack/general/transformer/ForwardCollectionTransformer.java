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
 * <p>Generic interface for a unidirectional Transformer between collection of a class to collection of another</p>
 *
 * @param <FROM> FROM which should be transformed to TO
 * @param <TO>   TO which is transformed from FROM
 * @author Christoph Praschl
 * @since 2.0
 */
@FunctionalInterface
@SuppressWarnings("java:S119") // Naming of type arguments
public interface ForwardCollectionTransformer<FROM, TO> {
    /**
     * Transforms a Collection of FROM to a Collection of TO
     *
     * @param collection the collection to be transformed
     * @return the resulting collection
     */
    Collection<TO> transformFromCollection(Collection<FROM> collection);
}
