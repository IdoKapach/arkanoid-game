// 322384561 - IDO KAPACH
/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    /**
     * The constant STARTING_SCORE.
     */
    public static final int STARTING_SCORE = 0, ADDING_POINTS_IN_HIT = 5;
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter {Counter} the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(ADDING_POINTS_IN_HIT);
    }
}
