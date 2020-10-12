/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.math.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <h2>SlidingWindowMedian</h2>
 * <p>Implementation of a median filter using sliding windows</p>
 *
 * <p>A two heap approach is used as described in
 * <a href="https://algorithmsandme.wordpress.com/2014/03/15/heaps-sliding-window-algorithm/">this blog post</a> </p>
 *
 * @author Rainer Meindl 16.09.2020
 * @since 2.1.0
 */
public class SlidingWindowMedian<T extends Comparable<T>> implements SlidingWindow<T> {

    @Override
    public List<T> filter(List<T> data, int kernel) {
        List<T> result = new ArrayList<>();
        if (data == null || data.isEmpty() || kernel <= 0) {
            return result;
        }
        // Use a max heap to store elements smaller or equal to median, and use a min heap to store elements greater than median.
        PriorityQueue<T> maxHeap = new PriorityQueue<>(kernel, Collections.reverseOrder());
        PriorityQueue<T> minHeap = new PriorityQueue<>(kernel);

        for (int i = 0; i < data.size(); i++) {
            // add new element into one of the heap
            if (maxHeap.isEmpty() || data.get(i).compareTo(maxHeap.peek()) < 0) {
                maxHeap.offer(data.get(i));
            } else {
                minHeap.offer(data.get(i));
            }
            // remove element outside of window
            if (i >= kernel) {
                if (data.get(i - kernel).compareTo(maxHeap.peek()) <= 0) {
                    maxHeap.remove(data.get(i - kernel));
                } else {
                    minHeap.remove(data.get(i - kernel));
                }
            }
            // balance two heaps, make sure maxHeap contains one more element if k is odd.
            while (minHeap.size() >= maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }
            while (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
            // add result
            if (i >= kernel - 1) {
                result.add(maxHeap.peek());
            }
        }
        return result;
    }
}
