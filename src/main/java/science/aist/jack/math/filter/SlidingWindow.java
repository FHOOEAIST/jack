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
 * <h2>SlidingWindow</h2>
 * <p>Sliding Window filtering allows filtering and aggregating large amounts of data. A kernel, or window, is applied
 * to group data together and filter that group with a specific method. Thus, the result always has to be smaller, than
 * the input</p>
 *
 * @author Rainer Meindl 16.09.2020
 */
public interface SlidingWindow<T> {

    /**
     * <p>Uses the kernel to group the data and filter the resulting subdatasets based on a filtering method</p>
     *
     * @param data   the data to filter
     * @param kernel the kernel, or window size
     * @return a List of the filtered data
     */
    List<T> filter(List<T> data, int kernel);

}
