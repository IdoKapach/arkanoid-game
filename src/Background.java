// 322384561 - IDO KAPACH
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Backround.
 */
public class Background implements Sprite {
    private Color color;
    private Rectangle rectangle = new Rectangle(
            new Point(0, 20), GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT - 20);

    /**
     * Instantiates a new Background.
     *
     * @param color {Color} the color of the background
     */
    public Background(Color color) {
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        rectangle.drawRec(d);
    }

    @Override
    public void timePassed() {
        return;
    }
}
