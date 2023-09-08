// 322384561 - IDO KAPACH

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyPressStoppableAnimation keyPressStoppableAnimation;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
        keyPressStoppableAnimation = new KeyPressStoppableAnimation(
                k, "paused -- press space to continue", this);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        keyPressStoppableAnimation.doOneFrame(d);
    }
    @Override
    public boolean shouldStop() {
        return keyPressStoppableAnimation.shouldStop();
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }
}
