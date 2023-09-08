// 322384561 - IDO KAPACH
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The decorator KeyPressStoppable which implements animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final String key;
    private final Animation animation;
    private KeyboardSensor sensor;
    private boolean stop = false, isAlreadyPressed = true;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor {KeyboardSensor} the sensor
     * @param key {String} the key
     * @param animation {Animation} the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(40, 280, key, 50);
        // checks if the space_key has pressed. if it has, turning on the stop.
        if (sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
            return;
        }
        isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
