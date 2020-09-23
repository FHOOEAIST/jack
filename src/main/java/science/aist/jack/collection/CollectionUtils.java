/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.collection;

import java.util.Collection;
import java.util.Map;

/**
 * <p>Util class for {@link java.util.Collection} and {@link java.lang.Iterable}</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
public class CollectionUtils {
    private CollectionUtils() {
    }

    /**
     * @param collection to check
     * @return true iff collection is null or empty else false
     */
    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * @param map to check
     * @return true iff collection is null or empty else false
     */
    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

}
