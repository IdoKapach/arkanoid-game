// 322384561 - IDO KAPACH
/**
 * The type Ball remover. It removes balls from the given game and contains counter
 * which counts the current number of balls in the game.
 */
public class BallRemover implements HitListener {
    /**
     * The constant STARTING_NUM_OF_BALLS.
     */
    public static final int STARTING_NUM_OF_BALLS = 3;
    private GameLevel game;
    private Counter numOfBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game {Game} the game.
     * @param numOfBalls {Counter} counter of the num of balls in the game.
     */
    public BallRemover(GameLevel game, Counter numOfBalls) {
        this.game = game;
        this.numOfBalls = numOfBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        numOfBalls.decrease(1);
    }
}
