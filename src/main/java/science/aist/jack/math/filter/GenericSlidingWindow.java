/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */


package science.aist.jack.math.filter;

import java.util.List;

/**
 * <h2>GenericSlidingWindow</h2>
 * <p>Generic Sliding Window allows to create a list of subselection by the kernel size to further apply
 * filtering and aggregating on those windows. This allows to integrate custom processing on each
 * subset.</p>
 *
 * @param <T> The input type inside the list for subset generation
 * @param <P> The output type that is expected for each subset
 * @author Baumgartner David 10.11.2020
 * @since 2.2.0
 */
public interface GenericSlidingWindow<T, P> {

    /**
     * <p>Uses the kernel to group the data, the resulting subset must be processed manually</p>
     *
     * @param data   the data to subset
     * @param kernel the kernel, or window size
     * @return a List of windows
     */
    List<P> filter(List<T> data, int kernel);

}
