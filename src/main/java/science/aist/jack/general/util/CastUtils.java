/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.general.util;

import lombok.experimental.UtilityClass;

/**
 * <p>Helper class for casting</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
@UtilityClass
public class CastUtils {
    /**
     * <p>Cast any given source to any type</p>
     * Supports to cast elements:
     * <pre>
     *     Object o = "abc";
     *     String s = cast(o);
     * </pre>
     * <p>
     * Supports to cast generic types:
     * <pre>
     *     List&lt;Object&gt; oList = ...
     *     List&lt;String&gt; sList = cast(oList);
     * </pre>
     *
     * @param source the element to be casted
     * @param <S>    the source type
     * @param <T>    the target type
     * @return the casted result
     * @throws ClassCastException if the cast fails
     */
    @SuppressWarnings("unchecked")
    public <S, T> T cast(S source) {
        return (T) source;
    }
}
