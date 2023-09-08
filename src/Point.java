// 322384561 - IDO KAPACH
/**
 * The type Point.
 */
public class Point {
    private double x, y;

    /**
     * Instantiates a new Point.
     *
     * @param x (double) the x of the point.
     * @param y (double) the y of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - return the distance of this point to the other point.
     *
     * @param other (Point) the other point
     * @return (double) the distance between the points.
     */
    public double distance(Point other) {
        double polinom = ((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y));
        return Math.sqrt(polinom);
    }

    /**
     * equals - return true if the points are equal, false otherwise.
     *
     * @param other (point) the other point.
     * @return (boolean) true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (Double.compare(this.x, other.x) == 0 && Double.compare(this.y, other.y) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets point's x.
     *
     * @return (double) the x
     */
    public double getX() {
        return x;
    }

    /**
     * Gets point's y.
     *
     * @return (double) the y.
     */
    public double getY() {
        return y;
    }
}
