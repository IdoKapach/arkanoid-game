// 322384561 - IDO KAPACH

import java.awt.Color;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     * he initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list
     */

    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background. Returns a sprite with the background of the level.
     *
     * @return the background
     */

    Sprite getBackground();

    /**
     * Blocks list. The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list of the blocks
     */

    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *     This number is = blocks.size();
     *
     * @return {int} the number Of Blocks To Remove.
     */

    int numberOfBlocksToRemove();

    /**
     * returns Paddle color.
     *
     * @return the color of the paddle.
     */
    Color paddleColor();
}
