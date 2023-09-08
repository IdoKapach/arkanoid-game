// 322384561 - IDO KAPACH
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
    private GUI gui = new GUI("game", SCREEN_WIDTH, SCREEN_HEIGHT);
    private int framesPerSecond = 60;
    private Sleeper sleeper = new Sleeper();

    /**
     * Run a given animation.
     *
     * @param animation {Animation} the animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // running one frame in each iteration
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            // calculate the remaining time for this frame.
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Gets gui.
     *
     * @return {GUI} the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Winning case  - adding winning message on the screen and closing the game.
     *
     * @param score {Counter} the final score
     * @param keyboard {KeyboardSensor} the keyboard
     */
    public void winningCase(Counter score, KeyboardSensor keyboard) {
        // adding winning message on the screen.
        Animation winningCase = new Animation() {
            private KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(
                    keyboard, "YOU WIN! Your score is: " + score.getValue(), this);
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
        this.run(winningCase);
        // closing the game.
        gui.close();
    }
}
