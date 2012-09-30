/*************************************************************************
 * Name: Gerasimos Athanasopoulos
 * Email: ath.ger@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    /**
     * Custom comparator for comparing points wrt their slope.
     */
    public final Comparator<Point> SLOPE_ORDER = new BySlope();

    /**
     * The x coordinate of the point
     */
    private final int x;
    /**
     * The y coordinate of the point
     */
    private final int y;

    /**
     * Creates a Point (constructor)
     * @param x the x-coordinate of the point in the plane
     * @param y the y-coordinate of the point in the plane
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Plots the point to standard drawing
     * @returns
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line between this point and that point to standard drawing
     * @param that is the other point
     * @returns
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Calculates the slope between the two points
     * @param that is the other point
     * @return the slope
     */
    public double slopeTo(Point that) {
        if (x == that.x && y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }
        if (x == that.x) { return Double.POSITIVE_INFINITY; }
        if (y == that.y) { return 0;                        }
        return (double) (that.y - y) / (double) (that.x - x);
    }

    /**
     * It compares two points.
     * @returns -1 0 1 if the point is less than, equal, or
     *          larger than the argument.
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Point that) {
        if (y < that.y || (y == that.y && x < that.x)) {
            return -1;
        }
        if (y > that.y || (y == that.y && x > that.x)) {
            return 1;
        }
        return 0;
    }

    /**
     * Return string representation of this point.
     * @returns
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    /**
     * The class that implements the Comparator interface in order
     * to compare points by their slope.
     */
    private class BySlope implements Comparator<Point> {
        private double pp1;
        private double pp2;

        public int compare(Point p1, Point p2) {
            pp1 = slopeTo(p1);
            pp2 = slopeTo(p2);
            if (pp1 == pp2) { return 0; }
            if (pp1 > pp2)  { return 1; }
            return -1;
        }
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
