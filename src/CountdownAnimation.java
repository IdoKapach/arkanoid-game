// 322384561 - IDO KAPACH
import biuoop.DrawSurface;

/**
 * The type Countdown animation:
 *  The CountdownAnimation will display the given gameScreen,
 *  for numOfSeconds seconds, and on top of them it will show
 *  a countdown from countFrom back to 1, where each number will
 *  appear on the screen for (numOfSeconds / countFrom) seconds, before
 *  it is replaced with the next one.
 */

public class CountdownAnimation implements Animation {
    private static final int X_POSITION = 395, Y_POSITION = 200;
    private SpriteCollection sprites;
    private int countFrom, countNum;
    private double numOfSeconds;
    private long startTime;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds {double} the num of seconds
     * @param countFrom {int} the count from
     * @param gameScreen {SpriteCollection} the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.sprites = gameScreen;
        countNum = countFrom;
        startTime = System.currentTimeMillis();
        stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        // determines the num should be presented on the screen by
        // calculating the time for each number in the countdown:
        long usedTime = System.currentTimeMillis() - startTime,
                milliPerDigit = ((long) numOfSeconds * 1000) / countFrom;
        countNum = (int) (countFrom - (usedTime / milliPerDigit));
        sprites.drawAllOn(d);
        // checks if countdown should stop:
        if (countNum <= 0) {
            d.drawText(X_POSITION, Y_POSITION, "1", 50);
            stop = true;
            return;
        }
        d.drawText(X_POSITION, Y_POSITION, Integer.toString(countNum), 50);
    }
    @Override
    public boolean shouldStop() { return stop; }
}
