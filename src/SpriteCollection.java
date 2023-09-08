// 322384561 - IDO KAPACH
import biuoop.DrawSurface;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new LinkedList<Sprite>();

    /**
     * Add sprite to spriteList field.
     *
     * @param s {Sprite} the sprite.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * call timePassed() on all sprites in spriteList.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites in spriteList.
     *
     * @param d {DrawSurface} the object that will draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).drawOn(d);
        }
    }

    /**
     * Remove sprite from spriteList.
     *
     * @param sprite {Sprite} the sprite the user wants to remove.
     */
    public void removeSprite(Sprite sprite) {
        spriteList.remove(sprite);
    }
}
