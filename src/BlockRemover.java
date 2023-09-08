// 322384561 - IDO KAPACH
/**
 * The type Block remover. a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 *  of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    /**
     * The constant INITILIED_NUM_OF_BLOCKS.
     */
    public static final int INITILIED_NUM_OF_BLOCKS = 57;
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game {Game} the game
     * @param remainingBlocks {Counter} the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
    }
}
