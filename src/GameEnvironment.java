// 322384561 - IDO KAPACH
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment-consists all the collidable objects in the game.
 */
public class GameEnvironment {
    private List<Collidable> colidList;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        colidList = new ArrayList<Collidable>();
    }

    /**
     * Instantiates a new Game environment.
     *
     * @param c {Collidable} collidable object which is added to the colidList.
     */
    public GameEnvironment(Collidable c) {
        colidList = new ArrayList<Collidable>();
        colidList.add(c);
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c {Collidable} the collidable.
     */
    public void addCollidable(Collidable c) {
        colidList.add(c);
    }

    /**
     * Gets the closest collision: Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory {Line} the route of the moving object.
     * @return {CollisionInfo} - the closest collision if there is, otherwise, returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollision = null;

        for (Collidable collidable: colidList) {
            Rectangle collidableRect = collidable.getCollisionRectangle();
            Point collidPoint = trajectory.closestIntersectionToStartOfLine(collidableRect);
            // if the collidable has potential collision:
            if (collidPoint != null) {
                // if this is the first potential collision:
                if (closestCollision == null) {
                    closestCollision = new CollisionInfo(collidPoint, collidable);
                    continue;
                }
                // Else, gets the information about the closer collision that is going to occur.
                if (Double.compare(trajectory.start().distance(closestCollision.collisionPoint()),
                        trajectory.start().distance(collidPoint)) > 0) {
                    closestCollision = new CollisionInfo(collidPoint, collidable);
                }
            }
        }
        return closestCollision;
    }

    /**
     * Remove collidable from colidList.
     *
     * @param c {Collidable} the collidable the user wants to remove.
     */
    public void removeCollidable(Collidable c) {
        colidList.remove(c);
    }
}
