// 322384561 - IDO KAPACH
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block. block is a collidable rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // the block's field
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    /**
     * The constant DEFAULT_WIDTH.
     */
    public static final int DEFAULT_WIDTH = 50, /**
     * The Default height.
     */
    DEFAULT_HEIGHT = 30;
    private Rectangle rect;
    private java.awt.Color color;

    /**
     * Instantiates a new Block with the saved width and height final field.
     *
     * @param upperLeft {Point} the upper left point of the rectangle.
     */
    public Block(Point upperLeft) {
        color = new Color(0);
        rect = new Rectangle(upperLeft, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Instantiates a new Block with given width and height.
     *
     * @param upperLeft {Point} the upper left
     * @param width     {Double} the width
     * @param height    {Double} the height
     */
    public Block(Point upperLeft, Double width, Double height) {
        color = new Color(0);
        rect = new Rectangle(upperLeft, width, height);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        // getting the collision case: 1 - if it is a horizontal collision,
        // 2 - if it is a vertical collision, 3 - if it is both collisions (if the collision
        // is on the corner of the block) , 0 - if there was no collision.
        this.notifyHit(hitter);
        int hitCase = rect.kindOfCollision(collisionPoint, currentVelocity);
        switch (hitCase) {
            case 1:
                // changing the horizontal velocity.
                return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            case 2:
                // changing the vertical velocity.
                return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            case 3:
                // changing both horizontal and vertical velocities.
                return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
            default:
                // doesn't change the velocity.
                return currentVelocity;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        this.rect.drawRec(d);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * sets the color of the block.
     *
     * @param color {Color} the wanted color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Remove the block from given game.
     *
     * @param game {Game} the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
