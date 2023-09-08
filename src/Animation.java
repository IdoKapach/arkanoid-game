// 322384561 - IDO KAPACH
import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame - activates methods that should be activated in each frame. the methods usually
     * need to get a Drawsurface as parameter.
     *
     * @param d {DrawSurface} the d - potentially parameter for the activated methods.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the running of this animationshould stop.
     *
     * @return {boolean} the boolean - true if it should stop and false if it shouldn't.
     */
    boolean shouldStop();
}
