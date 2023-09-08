// 322384561 - IDO KAPACH
/**
 * The interface Hit listener - waits for notification about hitting.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *     The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit {Block} the block that is being hit.
     * @param hitter {Ball} the hitter.
     */

    void hitEvent(Block beingHit, Ball hitter);
}
