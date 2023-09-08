// 322384561 - IDO KAPACH
/**
 * The interface Hit notifier - which informs to all his listeners when it got hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl {HitListener} the HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl {HitListener} the HitListener.
     */
    void removeHitListener(HitListener hl);
}
