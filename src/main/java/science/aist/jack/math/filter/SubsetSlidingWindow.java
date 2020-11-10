/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */


package science.aist.jack.math.filter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h2>SubsetSlidingWindow</h2>
 * <p>Subset Sliding Window allows to create a list of subselection by the kernel size to further apply
 * filtering and aggregating on those windows.</p>
 *
 * @param <T> The input and output type inside the list for subset generation
 * @author Baumgartner David 10.11.2020
 * @since 2.2.0
 */
public class SubsetSlidingWindow<T> implements GenericSlidingWindow<T, List<T>> {

    @Override
    public List<List<T>> filter(List<T> data, int kernel) {
        if (kernel > data.size())
            return Collections.emptyList();
        return IntStream.range(0, data.size() - kernel + 1)
                .mapToObj(start -> data.subList(start, start + kernel)).collect(Collectors.toList());
    }
}
