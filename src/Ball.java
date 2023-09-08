// 322384561 - IDO KAPACH
import biuoop.DrawSurface;

/**
 * The class that response to type Ball.
 * Balls have size (radius), color, and location (a Point). Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity vel;
    private int maximumX, minimumX = 0;
    private int maximumY, minimumY = 0;
    private GameEnvironment gameEnvironment;


    /**
     * Instantiates a new Ball.
     *
     * @param center (point) the center of the ball.
     * @param r      (int) the radius of the ball.
     * @param color  (java.awt.Color) the color of the ball.
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        radius = r;
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     (double) the x of the center point of the ball
     * @param y     (double) the y of the center point of the ball
     * @param r     (int) the radius of the ball.
     * @param color (java.awt.Color) the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        Point center = new Point(x, y);
        this.center = center;
        radius = r;
        this.color = color;
        gameEnvironment = new GameEnvironment();
    }

    /**
     * Draw on the ball on surface.
     *
     * @param surface the surface that the ball is drawn on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Gets the x of the center.
     *
     * @return (int) the x
     */
// accessors
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets the y of the center.
     *
     * @return (int) the y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return (int) the radius
     */
    public int getSize() {
        return radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return (java.awt.Color) the color
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Sets velocity. by getting Velocity.
     *
     * @param v (velocity) the given velocity we crate "vel" from.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * Sets velocity. by getting the required fields of Velocity.
     *
     * @param dx (double) the dx field
     * @param dy (double) the dy field
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return (Velocity) the velocity
     */
    public Velocity getVelocity() {
        return vel;
    }

    private void isTouchingEdge() {
        // checking if the ball next step will pass the edge
        int xResultMax = Double.compare(center.getX() + radius + vel.getDx(), maximumX);
        int xResultMin = Double.compare(center.getX() + vel.getDx() - radius, minimumX);
        int yResultMax = Double.compare(center.getY() + radius + vel.getDy(), maximumY);
        int yResultMin = Double.compare(center.getY() + vel.getDy() - radius, minimumY);
        // if the ball next step will pass the horizon edge:
        if (xResultMax > 0 || xResultMin < 0) {
            int xResultMax1 = Double.compare(center.getX() + radius - vel.getDx(), maximumX);
            int xResultMin1 = Double.compare(center.getX() - vel.getDx() - radius, minimumX);
            // checking if the ball is too big for allowing it to move. if it is, turning his horizon velocity to 0.
            if ((xResultMax > 0 && xResultMin1 < 0) || (xResultMax1 > 0 && xResultMin < 0)) {
                vel = new Velocity(0, vel.getDy());
            } else {
                // makes the ball touch the wall by resetting its center
                if (xResultMax > 0) {
                    center = new Point(maximumX + Math.abs(vel.getDx()) - radius, center.getY());
                } else {
                    center = new Point(minimumX - Math.abs(vel.getDx()) + radius, center.getY());
                }
                // checking if the ball's velocity direction is to the center. if it isn't,
                // turning his horizon velocity to -dx.
                if ((xResultMax > 0 && vel.getDx() > 0) || (xResultMin < 0 && vel.getDx() < 0)) {
                    vel = new Velocity(-vel.getDx(), vel.getDy());
                }
            }
        }
        // if the ball next step will pass the vertical edge:
        if (yResultMax > 0 || yResultMin < 0) {
            int yResultMax1 = Double.compare(center.getY() + radius - vel.getDy(), maximumY);
            int yResultMin1 = Double.compare(center.getY() - vel.getDy() - radius, minimumY);
            // checking if the ball is too big for allowing it to move. if it is, turning his vertical velocity to 0.
            if ((yResultMax > 0 && yResultMin1 < 0) || (yResultMax1 > 0 && yResultMin < 0)) {
                vel = new Velocity(vel.getDx(), 0);
            } else {
                // makes the ball touch the wall by resetting its center
                if (yResultMax > 0) {
                    center = new Point(center.getX(), maximumY + Math.abs(vel.getDy()) - radius);
                } else {
                    center = new Point(center.getX(), minimumY - Math.abs(vel.getDy()) + radius);
                }
                // checking if the ball's velocity direction is to the center. if it isn't,
                // turning his vertical velocity to -dy.
                if ((yResultMax > 0 && vel.getDy() > 0) || (yResultMin < 0 && vel.getDy() < 0)) {
                    vel = new Velocity(vel.getDx(), -vel.getDy());
                }
            }
        }
    }

    /**
     * Moves the ball one step.
     */
    public void moveOneStep1() {
        isTouchingEdge();
        this.center = this.vel.applyToPoint(this.center);
    }

    /**
     * sets the size of the screen or the right and upper edges of the frame.
     *
     * @param maximumX (int) - the horizon size / the right edge.
     * @param maximumY (int) - the vertical size / the upper edge.
     */
    public void setMaximumXY(int maximumX, int maximumY) {
        this.maximumX = maximumX;
        this.maximumY = maximumY;
    }

    /**
     * sets the left and bottom edges of the frame.
     *
     * @param minimumX (int) - the left edge.
     * @param minimumY (int) - the bottom edge.
     */
    public void setMinimumXY(int minimumX, int minimumY) {
        this.minimumX = minimumX;
        this.minimumY = minimumY;
    }

    /**
     * sets center.
     *
     * @param point (Point) - the new center.
     */
    public void setCenter(Point point) {
        center = point;
    }

    /**
     * Moves the ball one step.
     */
    public void moveOneStep() {
        // 1) compute the ball trajectory (the trajectory is "how the ball will move
        // without any obstacles" -- its line starting at current location, and
        //ending where the velocity will take the ball if no collisions will occur).
        Line trajectory = new Line(center.getX(), center.getY(), center.getX() + vel.getDx(),
                center.getY() + vel.getDy());
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        // 2) Check (using the game environment) if moving on this trajectory will hit anything.
        // 2.1) If no, then move the ball to the end of the trajectory.
        if (collisionInfo == null) {
            this.center = this.vel.applyToPoint(this.center);
            return;
        }
        //2.2) Otherwise (there is a hit):
        //2.2.2) move the ball to "almost" the hit point, but just slightly before it.
        double theDistanceThatUnderItTheBallIsAlmostInTheHitPoint = 0.5;
        if (Double.compare(this.center.distance(collisionInfo.collisionPoint()),
                theDistanceThatUnderItTheBallIsAlmostInTheHitPoint) > 0) {
            //set temporary dx and dy that smaller than the originals.
            Double tmpDx = (vel.getDx() / 1.5), tmpDy = (vel.getDy() / 1.5);
            //set temporary Trajectory that fits to tmpDx and tmpDy.
            Line tmpTrajectory = new Line(center.getX(), center.getY(), center.getX() + tmpDx,
                    center.getY() + tmpDy);
            //while it's impossible to take the ball one step with tmpDx and tmpDy, the func divides them by 2.
            while (gameEnvironment.getClosestCollision(tmpTrajectory) != null) {
                tmpDx /= 2;
                tmpDy /= 2;
                tmpTrajectory = new Line(center.getX(), center.getY(), center.getX() + tmpDx,
                        center.getY() + tmpDy);
            }
            //move the ball to "almost" the hit point, but just slightly before it.
            this.center = new Point(center.getX() + tmpDx, center.getY() + tmpDy);
        }

        //2.2.3) notify the hit object (using its hit() method) that a collision occurred.
        Collidable collisionObject = collisionInfo.collisionObject();
        //2.2.4) update the velocity to the new velocity returned by the hit() method.
        Velocity tmpVel = this.vel;
        Point tmpCenter = this.center;
        this.vel = collisionObject.hit(collisionInfo.collisionPoint(), this.vel, this);
        this.center = new Point(center.getX() + vel.getDx(), center.getY() + vel.getDy());
        trajectory = new Line(center.getX(), center.getY(), center.getX() - vel.getDx(),
                center.getY() - vel.getDy());
        // checking if the step will hit other collidable.  if it does, the func sets vel to have the
        // opposite dx and dy fields.
        if (gameEnvironment.getClosestCollision(trajectory) != null) {
            this.vel = new Velocity(-1 * tmpVel.getDx(), -1 * tmpVel.getDy());
            this.center = new Point(tmpCenter.getX() + vel.getDx(), tmpCenter.getY() + vel.getDy());
        }
        // sets the dy fields to be 1/-1 if it is too low in absolute value (lower than 0.3).
        if (Double.compare(this.vel.getDy(), 0.3) <= 0 &&  Double.compare(this.vel.getDy(), 0) >= 0) {
            this.vel = new Velocity(vel.getDx(), 1);
        }
        if (Double.compare(this.vel.getDy(), -0.3) >= 0 &&  Double.compare(this.vel.getDy(), 0) < 0) {
            this.vel = new Velocity(vel.getDx(), -1);
        }
    }

    /**
     * sets gameEnvironment.
     *
     * @param gameEnvironment {GameEnvironment}.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Remove the ball from given game.
     *
     * @param g {Game} the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

