// 322384561 - IDO KAPACH

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 3. hardest level. has 2 balls and 57 blocks
 */
public class Level3 implements LevelInformation {
    private static final int PADDLE_SPEED = 10, PADDLE_WIDTH = 90, NUM_BLOCKS_TO_REMOVE = 57;
    private static final Color PADDLE_COLOR = Color.YELLOW;
    private int numOfBalls = 2;
    private Background background = new Background(Color.orange);
    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity velBall1 = new Velocity(3, -7), velBall2 = new Velocity(5, -7);
        List<Velocity> ballsVel = new ArrayList<Velocity>();
        ballsVel.add(velBall1);
        ballsVel.add(velBall2);
        return ballsVel;
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
        return "Level 3";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    private List<Block> addingInNicePattern() {
        List<Block> listBlock = new ArrayList<Block>();
        java.awt.Color[] colors = {Color.PINK, Color.CYAN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.RED};
        // add the rest of the blocks. the func organizes the blocks in 6 rows.
        for (int i = 0; i < 6; i++) {
            // sets the colors to the blocks in the i row.
            Color currentColor = colors[i];
            for (int j = 0; j < 12 - i; j++) {
                Point blockPlace = new Point(170 + Block.DEFAULT_WIDTH * (j + i), 100 + Block.DEFAULT_HEIGHT * i);
                Block block = new Block(blockPlace);
                block.setColor(currentColor);
                listBlock.add(block);
            }
        }
        return listBlock;
    }
    @Override
    public List<Block> blocks() {
        return addingInNicePattern();
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
