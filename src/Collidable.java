// 322384561 - IDO KAPACH
/**
 * The interface Collidable. used by things that can be collided with.
 */
public interface Collidable {
    /**
     * Gets the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint {Point} the collision point
     * @param currentVelocity {Velocity} the velocity before the hit.
     * @return {Velocity} the velocity after the hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
