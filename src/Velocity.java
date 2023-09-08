// 322384561 - IDO KAPACH
/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx, dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx (double) the dx field
     * @param dy (double) the dy field
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p (Point) the p the fun gets
     * @return (point) the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Gets dx.
     *
     * @return (double) the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets dy.
     *
     * @return (double) the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * creates Velocity from angle and speed velocity.
     *
     * @param angle (double) the angle
     * @param speed (double) the speed
     * @return (Velocity) the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle - 90;
        if (Double.compare(angle, 90) > 0) {
            angle -= 270;
        }
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * setting the angle from given velocity so "up" means 0 degrees (with clock direction).
     * @param velocity {Velocity} the velocity.
     * @return {double} the angle.
     */
    public static double angleFromVelocity(Velocity velocity) {
        double angle = Math.atan2(velocity.getDy(), velocity.getDx());
        angle = Math.toDegrees(angle);
        if (Double.compare(angle, 0) < 0) {
            angle += 360;
        }
        angle += 90;
        if (Double.compare(angle, 360) >= 0) {
            angle -= 360;
        }
        return angle;
    }

    /**
     * calculates the speed of velocity with Pitagoras sentence.
     * @param velocity {Velocity} the velocity.
     * @return {double} the speed.
     */
    public static double speedFromVelocity(Velocity velocity) {
        return Math.sqrt((velocity.getDx() * velocity.getDx()) + (velocity.getDy() * velocity.getDy()));
    }
}
