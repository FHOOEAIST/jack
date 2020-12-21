/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.reflection.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>Test domain class</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class A {
    protected double[] arr;
    private int a;
    private String b;
}
