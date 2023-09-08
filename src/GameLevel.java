// 322384561 - IDO KAPACH
import biuoop.DrawSurface;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import java.awt.Color;


/**
 * The type Game.
 */
public class GameLevel implements Animation {
    /**
     * The constant SCREEN_WIDTH.
     */
    public static final int SCREEN_WIDTH = 800, /**
     * The Screen height.
     */
    SCREEN_HEIGHT = 600;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter remainingBlocks, remainingBalls,
            scoreCounter;
    private BlockRemover blockRemover;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private LevelInformation levelInformation;

    /**
     * Instantiates a new Game level.
     *
     * @param levelInformation {LevelInformation} the level information
     * @param ar {AnimationRunner} the ar
     * @param ks {KeyboardSensor} the ks
     * @param scoreCounter {Counter} the score counter
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor ks, Counter scoreCounter) {
        this.levelInformation = levelInformation;
        remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        remainingBalls = new Counter(levelInformation.numberOfBalls());
        blockRemover = new BlockRemover(this, remainingBlocks);
        runner = ar;
        keyboard = ks;
        this.scoreCounter = scoreCounter;
    }

    /**
     * Add collidable to environment field.
     *
     * @param c {Collidable} the collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite to sprites field.
     *
     * @param sprite {Sprite} the sprite.
     */
    public void addSprite(Sprite sprite) {
        sprites.addSprite(sprite);
    }

    private void addingInNicePattern() {
        // add the frame blocks
        Block frameUp = new Block(new Point(0, 20), 800.0, 10.0),
                frameDown = new Block(new Point(0, 610), 800.0, 30.0),
                frameLeft = new Block(new Point(0, 20), 30.0, 600.0),
                frameRight = new Block(new Point(770, 20), 30.0, 600.0);
        environment.addCollidable(frameUp);
        environment.addCollidable(frameDown);
        environment.addCollidable(frameLeft);
        environment.addCollidable(frameRight);
        sprites.addSprite(frameUp);
        sprites.addSprite(frameDown);
        sprites.addSprite(frameLeft);
        sprites.addSprite(frameRight);

        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        frameDown.addHitListener(ballRemover);

        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        sprites.addSprite(scoreIndicator);
        // add the rest of the blocks.
        for (Block block:levelInformation.blocks()) {
                environment.addCollidable(block);
                sprites.addSprite(block);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
            }
        }


    private void addingBalls() {
        // creating balls with the velocities in levelInformation
        for (Velocity ballvel:levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(400, 520, 5, Color.BLACK);
            ball.setVelocity(ballvel.getDx(), ballvel.getDy());
            ball.setGameEnvironment(environment);
            sprites.addSprite(ball);
        }
    }

    private void addPaddle() {
        Paddle paddle = new Paddle(runner.getGui().getKeyboardSensor(),
                levelInformation.paddleWidth(), levelInformation.paddleColor());
        environment.addCollidable(paddle);
        sprites.addSprite(paddle);
    }

    private void addBackround() {
        sprites.addSprite(levelInformation.getBackground());
    }
    private void addLevelName() {
        Sprite levelName = new Sprite() {
            private static final int X_POSITION = 430, Y_POSITION = 15, FONT_SIZE = 15;
            @Override
            public void drawOn(DrawSurface d) {
                d.drawText(X_POSITION, Y_POSITION, "Level Name: " + levelInformation.levelName(), FONT_SIZE);
            }

            @Override
            public void timePassed() {
                return;
            }
        };
        sprites.addSprite(levelName);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        addLevelName();
        addBackround();
        addPaddle();
        addingInNicePattern();
        // sets the starting dx of the balls.
        addingBalls();
    }


    /**
     * Remove collidable from environment.
     *
     * @param c {Collidable} the Collidable the user wants to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite from sprites.
     *
     * @param s {Sprite} the sprite the user wants to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Run the game - start the animation loop. the animation stops in winning case - if all the blocks
     * (except of the edges) were "bombed" by the player or in losing case - if all the balls are out of the screen.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts.
//        this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        Sleeper sleeper = new Sleeper();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        // winning level case:
        if (remainingBlocks.getValue() <= 0) {
            // increasing the score by 100 points.
            scoreCounter.increase(100);
            running = false;
        }
        // losing case:
        if (remainingBalls.getValue() == 0) {
            // adding losing message on the screen.
            Animation losingCase = new Animation() {
                private KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(
                        keyboard, "GAME OVER! Your score is: " + scoreCounter.getValue(), this);
                private boolean stop;
                @Override
                public void doOneFrame(DrawSurface d) {
                    keyPressStoppableAnimation.doOneFrame(d);
                }

                @Override
                public boolean shouldStop() {
                    return keyPressStoppableAnimation.shouldStop();
                }
            };
            this.runner.run(losingCase);
            // stopping the game.
            running = false;
            runner.getGui().close();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
