// 322384561 - IDO KAPACH
import biuoop.DrawSurface;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private static final int X_POSITION = 300, Y_POSITION = 15, FONT_SIZE = 15;
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score {Counter} counter of the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(X_POSITION, Y_POSITION, "score: " + score.getValue(), FONT_SIZE);
    }

    @Override
    public void timePassed() {
        return;
    }
}
