// 322384561 - IDO KAPACH
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * The type Rectangle.
 */
public class Rectangle {
    // the rectangle's corners
    private Point upperLeft, downLeft, upperRight, downRight;
    private double width, height;

    /**
     * Instantiates a new Rectangle - Create a new rectangle with location and width/height.
     *
     * @param upperLeft {Point} the upper left
     * @param width     {double} the width
     * @param height    {double} the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Intersection points list. Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line {Line} the line
     * @return {List(Point)} the list of points.
     */
    public List intersectionPoints(Line line) {
        // creating lines that represent the rectangle's edges
        Line leftL = new Line(upperLeft, downLeft);
        Line rightL = new Line(upperRight, downRight);
        Line upL = new Line(upperLeft, upperRight);
        Line downL = new Line(downLeft, downRight);
        Line[] edges = {leftL, rightL, downL, upL};
        List list = new ArrayList<Point>();
        // adding the intersection points with the given line to the list.
        for (Line edge:edges) {
            Point pointS = line.intersectionWith(edge);
            if (pointS != null) {
                list.add(pointS);
            }
        }
        return list;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return {double} the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return {double} the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the upper left point of the rectangle.
     *
     * @return {Point} the upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    private int cornerCollision(Point collisionPoint, Velocity velocity) {
        // setting the angle so "up" means 0 degrees (with clock direction).
        double angle = Velocity.angleFromVelocity(velocity);
        // sets the rectangle's edges and the step line.
        Line leftL = new Line(upperLeft, downLeft);
        Line rightL = new Line(upperRight, downRight);
        Line upL = new Line(upperLeft, upperRight);
        Line downL = new Line(downLeft, downRight);
        Line pointL = new Line(collisionPoint, new Point(collisionPoint.getX() + velocity.getDx(),
                collisionPoint.getY() + velocity.getDy()));
        // collH - horizontal collision case, collV - vertical collision case,
        // bothColl - both horizontal and vertical collision case.
        int collH = 1, collV = 2, bothColl = 3;
        // checking if the corner collision is at the down-left corner:
        if (downL.isIntersecting(pointL) && leftL.isIntersecting(pointL)) {
            if (Double.compare(angle, 45) > 0 && Double.compare(angle, 180) < 0) {
                return collH;
            }
            if ((Double.compare(angle, 270) > 0) || (Double.compare(angle, 45) < 0)) {
                return collV;
            }
            return bothColl;
        }
        // checking if the corner collision is at the down-right corner:
        if (downL.isIntersecting(pointL) && rightL.isIntersecting(pointL)) {
            if ((Double.compare(angle, 180) > 0) && (Double.compare(angle, 315) < 0)) {
                return collH;
            }
            if (Double.compare(angle, 315) > 0 || Double.compare(angle, 90) < 0) {
                return collV;
            }
            return bothColl;
        }
        // checking if the corner collision is at the up-right corner:
        if (upL.isIntersecting(pointL) && rightL.isIntersecting(pointL)) {
            if (Double.compare(angle, 225) > 0) {
                return collH;
            }
            if (Double.compare(angle, 90) > 0 && Double.compare(angle, 225) < 0) {
                return collV;
            }
            return bothColl;
        }
        // checking if the corner collision is at the up-left corner:
        if (upL.isIntersecting(pointL) && leftL.isIntersecting(pointL)) {
            if (Double.compare(angle, 135) < 0) {
                return collH;
            }
            if (Double.compare(angle, 135) > 0 && Double.compare(angle, 270) < 0) {
                return collV;
            }
            return bothColl;
        }
        return 0;
    }

    /**
     * gets the type of the collision with the rectangle given the collision point.
     *
     * @param collisionPoint {Point} the collision point.
     * @param velocity {Velocity } the velocity of the moving object-effects the collision type when it appears
     *                 in the corner.
     * @return {int} getting the collision case:
     *         1 - if it is a horizontal collision,
     *         2 - if it is a vertical collision,
     *         3 - if it is both collisions (if the collision is on the corner of the rectangle),
     *         0 - if there was no collision.
     */
    public int kindOfCollision(Point collisionPoint, Velocity velocity) {
        // creating lines that represent the rectangle's edges
        Line leftL = new Line(upperLeft, downLeft);
        Line rightL = new Line(upperRight, downRight);
        Line upL = new Line(upperLeft, upperRight);
        Line downL = new Line(downLeft, downRight);
        Line pointL = new Line(collisionPoint, new Point(collisionPoint.getX() + velocity.getDx(),
                collisionPoint.getY() + velocity.getDy()));
        // to get the wanted results, collH (horizontal collision) adding to the final result 1 and collV
        // (vertical results) adding 2.
        int collH = 1, collV = 2, thisColl = 0;
        // if there is a horizontal collision - adding 1 to final result.
        if (leftL.isIntersecting(pointL) || rightL.isIntersecting(pointL)) {
            thisColl += collH;
        }
        // if there is a vertical collision - adding 2 to final result.
        if (upL.isIntersecting(pointL) || downL.isIntersecting(pointL)) {
            thisColl += collV;
        }
        // some corner collision are considered as horizontal-if cornerCollision = 1 or as vertical -
        // if cornerCollision = 2. otherwise, the corner collision is considered as corner case.
        if (thisColl == 3) {
            return cornerCollision(collisionPoint, velocity);
        }
        return thisColl;
    }
    /**
     * Draw the rectangle with drawdurface.
     *
     * @param drawSurface {DrawSurface} the draw surface.
     */
    public void drawRec(DrawSurface drawSurface) {
        drawSurface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }
}
