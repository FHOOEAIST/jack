/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.persistence.filesystem.domain;


import java.io.Serializable;

/**
 * <p>Test domain object</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public class JavaPoint implements Serializable {
    private double x;
    private double y;

    /**
     * default constructor initializing a point with (0,0)
     */
    public JavaPoint() {
    }

    /**
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public JavaPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaPoint that = (JavaPoint) o;

        final double epsilon = 0.00000001f;
        return Math.abs(x - that.x) < epsilon &&
                Math.abs(y - that.y) < epsilon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) x;
        hash = 71 * hash + (int) y;
        return hash;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
