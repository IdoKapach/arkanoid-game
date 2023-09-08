// 322384561 - IDO KAPACH

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 2. has "rainbow" blocks and very wide paddle.
 */
public class Level2 implements LevelInformation {
    private static final int PADDLE_SPEED = 10, PADDLE_WIDTH = 450, NUM_BLOCKS_TO_REMOVE = 15;
    private static final Color PADDLE_COLOR = Color.ORANGE;
    private int numOfBalls = 10;
    private Background background = new Background(Color.WHITE);

    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        double angle = 200, speed = 7;
        for (int i = 0; i < 10; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(angle + i * 15, speed));
        }
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
        return "Rainbow level";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        // 15 blocks are added in a row with rainbow colors.
        List<Block> blocks = new ArrayList<Block>();
        double blockHeightPosition = 180, blockWidth = (double) (GameLevel.SCREEN_WIDTH - 60) / NUM_BLOCKS_TO_REMOVE;
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        for (int i = 0; i < NUM_BLOCKS_TO_REMOVE; i++) {
            Block block = new Block(new Point(i * blockWidth + 30, blockHeightPosition),
                    blockWidth, (double) Block.DEFAULT_HEIGHT);
            if (i < 6) {
                block.setColor(colors[Math.round(i / 2)]);
            } else if (i >= 6 && i < 9) {
                block.setColor(colors[3]);
            } else {
                block.setColor(colors[Math.round((i - 1) / 2)]);
            }
            blocks.add(block);
        }
        return blocks;
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
