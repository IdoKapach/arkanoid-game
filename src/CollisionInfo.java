// 322384561 - IDO KAPACH
/**
 * The type Collision info-has some information about collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point-point at which the collision occurs..
     *
     * @return {Point} the Collision point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Collision object-the collidable object involved in the collision..
     *
     * @return {Collidable} the collidable object
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
