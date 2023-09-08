// 322384561 - IDO KAPACH
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d {DrawSurface} the object that draws the sprite.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}

