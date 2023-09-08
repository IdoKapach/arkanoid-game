// 322384561 - IDO KAPACH

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1. the first layout. the ball flies directly to the single block and destroy it.
 */
public class Level1 implements LevelInformation {
    private static final int PADDLE_SPEED = 10, PADDLE_WIDTH = 90, NUM_BLOCKS_TO_REMOVE = 1;
    private static final Color PADDLE_COLOR = Color.BLACK;
    private int numOfBalls = 1;
    private Background background = new Background(Color.PINK);
    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        velocityList.add(new Velocity(0, -4));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Very easy level";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        // adding single block at the middle of the screen.
        List<Block> blockList = new ArrayList<Block>();
        Double blockHeight = (double) Block.DEFAULT_WIDTH;
        blockList.add(new Block(new Point(GameLevel.SCREEN_WIDTH / 2 - Block.DEFAULT_WIDTH / 2, 100),
                (double) Block.DEFAULT_WIDTH, blockHeight));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_BLOCKS_TO_REMOVE;
    }

    @Override
    public Color paddleColor() {
        return PADDLE_COLOR;
    }
}
