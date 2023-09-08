// 322384561 - IDO KAPACH

import java.util.List;

/**
 * class that responsible to create Line.
 */
public class Line {
    /**
     * One shared point between 2 lines - case 0.
     */
    static final double ONE_SHARED_P = 0,
    /**
     * Infinate shared points between 2 lines - case 1.
     */
    INFINATE_SHARED_P = 1,
    /**
     * No shared points between 2 lines - case 2.
     */
    NO_SHARED_P = 2;
    private Point start, end;

    /**
     * constructors - Instantiates a new Line. gets 2 points that delimit the line.
     *
     * @param start the start - the first point
     * @param end   the end - the second point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructors - Instantiates a new Line. gets 2 filed for 2 points that delimit the line.
     *
     * @param x1 the x field for the first point
     * @param y1 the y field for the first point
     * @param x2 the x field for the second point
     * @param y2 the y field for the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point first = new Point(x1, y1), second = new Point(x2, y2);
        this.start = first;
        this.end = second;
    }

    /**
     * Return the length of the line.
     *
     * @return (double) the length
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return (point) the middle
     */
    public Point middle() {
        // find the middle by calculating the average of the x and y fields of the 2 points.
        double averageX = (start.getX() + end.getX()) / 2, averageY = (start.getY() + end.getY()) / 2;
        double newX = averageX, newY = averageY;
        return new Point(newX, newY);
    }

    /**
     * Start point.
     *
     * @return (point) the start point
     */
// Returns the start point of the line
    public Point start() {
        return start;
    }

    /**
     * End point.
     *
     * @return (point) the end point
     */
// Returns the end point of the line
    public Point end() {
        return end;
    }

    private double[] findEquation(Point first, Point second) {
        // getting the line's incline
        double incline = first.getX() - second.getX() == 0
                ? 0 : (first.getY() - second.getY()) / (first.getX() - second.getX());
        // getting the line's const num
        double constNum = first.getY() - incline * first.getX();
        double[] equal = {incline, constNum};
        return equal;
    }

    private Point[] findSharedPoint(Line other) {
        //  getting 2 lines equation's incline and const num
        double[] pEqua = findEquation(this.start, this.end);
        double[] oEqua = findEquation(other.start, other.end);
        double pIncline = pEqua[0], pConstNum = pEqua[1];
        double oIncline = oEqua[0], oConstNum = oEqua[1];
        double pMaxX = Math.max(this.start.getX(), this.end.getX()),
                oMaxX = Math.max(other.start.getX(), other.end.getX()),
                pMinX = Math.min(this.start.getX(), this.end.getX()),
                oMinX = Math.min(other.start.getX(), other.end.getX());
        double pMaxY = Math.max(Math.abs(this.start.getY()), Math.abs(this.end.getY())),
                oMaxY = Math.max(Math.abs(other.start.getY()), Math.abs(other.end.getY())),
                pMinY = Math.min(Math.abs(this.start.getY()), Math.abs(this.end.getY())),
                oMinY = Math.min(Math.abs(other.start.getY()), Math.abs(other.end.getY()));

        Threshold limit = new Threshold();
        // in case both lines aren't functions (if they are lines with const x field):
        if (Double.compare(pMaxX, pMinX) == 0 && Double.compare(oMaxX, oMinX) == 0) {
            if (pMaxX != oMaxX) {
                Point[] arrP = {null, new Point(0, NO_SHARED_P)};
                return arrP;
            }
            // checking if they have overlap y range:
            // if they don't have overlap y range:
            if (limit.haveInteraction(pMaxY, oMinY) < 0 || limit.haveInteraction(oMaxY, pMinY) < 0) {
                Point[] arrP = {null, new Point(0, NO_SHARED_P)};
                return arrP;
            }
            // if they have overlap over one point only:
            if (limit.haveInteraction(pMaxY, oMinY) == 0 || limit.haveInteraction(oMaxY, pMinY) == 0) {
                if (limit.haveInteraction(pMaxY, oMinY) == 0) {
                    double equalY = pMaxY;
                    Point[] arrP = {new Point(pMaxX, equalY), new Point(0, ONE_SHARED_P)};
                    return arrP;
                }
                double equalY = pMinY;
                Point[] arrP = {new Point(pMinX, equalY), new Point(0, ONE_SHARED_P)};
                return arrP;
            }
            // if they have overlap range:
            Point[] arrP = {null, new Point(0, INFINATE_SHARED_P)};
            return arrP;
        }
        // in case one of the lines isn't function (line with const x field):
        // if this line isn't function:
        if ((Double.compare(pMaxX, pMinX) == 0)) {
            // getting the x and y fields of the shared point of the lines' equations
            double xEqual = pMaxX;
            double equalY = oIncline * xEqual + oConstNum;
            // if the shared point of the lines in the lines' range (x or y range), the lines have shared point
            if (limit.haveInteraction(xEqual, oMaxX) <= 0 && limit.haveInteraction(xEqual, oMinX) >= 0) {
                if (limit.haveInteraction(equalY, pMaxY) <= 0 && limit.haveInteraction(equalY, pMinY) >= 0) {
                    Point[] arrP = {new Point(xEqual, equalY), new Point(0, ONE_SHARED_P)};
                    return arrP;
                }
            }
            Point[] arrP = {null, new Point(0, NO_SHARED_P)};
            return arrP;
        }

        // if the other line isn't function:
        if ((Double.compare(oMaxX, oMinX) == 0)) {
            // getting the x and y fields of the shared point of the lines' equations
            double xEqual = oMaxX;
            double equalY = pIncline * xEqual + pConstNum;
            // if the shared point of the lines in the lines' range (x or y range), the lines have shared point
            if (limit.haveInteraction(xEqual, pMaxX) <= 0 && limit.haveInteraction(xEqual, pMinX) >= 0) {
                if (limit.haveInteraction(equalY, oMaxY) <= 0 && limit.haveInteraction(equalY, oMinY) >= 0) {
                    Point[] arrP = {new Point(xEqual, equalY), new Point(0, ONE_SHARED_P)};
                    return arrP;
                }
            }
            Point[] arrP = {null, new Point(0, NO_SHARED_P)};
            return arrP;
        }

        // in case the lines have same incline
        if (Double.compare(pIncline, oIncline) == 0) {
            // in case the lines have same equation - if they don't have, certainly they don't have shared point.
            if (Double.compare(pConstNum, oConstNum) == 0) {
                // checking if they have overlap range:
                // if they don't have overlap range:
                if (limit.haveInteraction(pMaxX, oMinX) < 0 || limit.haveInteraction(oMaxX, pMinX) < 0) {
                    Point[] arrP = {null, new Point(0, NO_SHARED_P)};
                    return arrP;
                }
                // if they have overlap over one point only:
                if (limit.haveInteraction(pMaxX, oMinX) == 0 || limit.haveInteraction(oMaxX, pMinX) == 0) {
                    if (limit.haveInteraction(pMaxX, oMinX) == 0) {
                        double equalY = pIncline * pMaxX + pConstNum;
                        Point[] arrP = {new Point(pMaxX, equalY), new Point(0, ONE_SHARED_P)};
                        return arrP;
                    }
                    double equalY = pIncline * pMinX + pConstNum;
                    Point[] arrP = {new Point(pMinX, equalY), new Point(0, ONE_SHARED_P)};
                    return arrP;
                }
                // if they have overlap range:
                Point[] arrP = {null, new Point(0, INFINATE_SHARED_P)};
                return arrP;
            }
            Point[] arrP = {null, new Point(0, NO_SHARED_P)};
            return arrP;
        }
        // getting the x and y fields of the shared point of the lines' equations
        double xEqual = (oConstNum - pConstNum) / (pIncline - oIncline);
        double equalY = oIncline * xEqual + oConstNum;
        // if the shared point of the lines' equations in the lines' range, the lines have shared point
        if (limit.haveInteraction(xEqual, pMaxX) <= 0 && limit.haveInteraction(xEqual, pMinX) >= 0) {
            if (limit.haveInteraction(xEqual, oMaxX) <= 0 && limit.haveInteraction(xEqual, oMinX) >= 0) {
                Point[] arrP = {new Point(xEqual, equalY), new Point(0, ONE_SHARED_P)};
                return arrP;
            }
        }
        Point[] arrP = {null, new Point(0, NO_SHARED_P)};
        return arrP;
    }

    /**
     * Returns true if the lines intersect, false otherwise. using "findSharedPoint" method to check if the 2
     * lines share a point
     *
     * @param other (line) the other line we want to check with this line
     * @return (boolean) Returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point[] result = findSharedPoint(other);
        if (Double.compare(result[1].getY(), NO_SHARED_P) == 0) {
            return false;
        }
        return true;
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise. using
     * "findSharedPoint" method to check if the 2  lines share a point.
     *
     * @param other (line) the other line we want to check with this line
     * @return (point) Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point[] result = findSharedPoint(other);
        if (Double.compare(result[1].getY(), ONE_SHARED_P) != 0) {
            return null;
        }
        return result[0];
    }

    /**
     * Equals - return true is the lines are equal, false otherwise.
     *
     * @param other (line) the other line we want to check with this line
     * @return (boolean) return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        // finding the max and min x value in every point.
        double pMaxX = Math.max(this.start.getX(), this.end.getX()),
                oMaxX = Math.max(other.start.getX(), other.end.getX()),
                pMinX = Math.min(this.start.getX(), this.end.getX()),
                oMinX = Math.min(other.start.getX(), other.end.getX());

        if (Double.compare(pMaxX, oMaxX) == 0 && Double.compare(pMinX, oMinX) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the closest intersection to start point of the line with given rectangle.
     *
     * @param rect {Rectangle}  the rectangle.
     * @return {Point} If this line does not intersect with the rectangle, return null.
     *     Otherwise, return the closest intersection point to the start of the line.
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List listP = (List<Point>) rect.intersectionPoints(this);
        // If this line does not intersect with the rectangle, return null.
        if (listP == null || listP.size() == 0) {
            return null;
        }
        // Otherwise, return the closest intersection point to the
        // start of the line.
        Point closestP = (Point) listP.get(0);
        for (Point point:(List<Point>) listP) {
            if (point.distance(this.start) < closestP.distance(this.start)) {
                closestP = point;
            }
        }
        return closestP;
    }

}
