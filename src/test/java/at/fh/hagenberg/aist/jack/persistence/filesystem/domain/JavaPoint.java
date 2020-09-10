package at.fh.hagenberg.aist.jack.persistence.filesystem.domain;


import java.io.Serializable;

/**
 * Created by Andreas Pointner on 24.04.2017.
 *
 * @author Andreas Pointner
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
