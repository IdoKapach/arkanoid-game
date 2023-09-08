// 322384561 - IDO KAPACH
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow - run several levels in a row.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score = new Counter(ScoreTrackingListener.STARTING_SCORE);

    /**
     * Instantiates a new Game flow.
     *
     * @param ar {AnimationRunner} the AnimationRunner.
     * @param ks {KeyboardSensor} the KeyboardSensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        animationRunner = ar;
        keyboardSensor = ks;
    }
    /**
     * Runs the levels in a row.
     *
     * @param levels {List<LevelInformation>} the list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // running all the levels
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, animationRunner, keyboardSensor, score);
            level.initialize();
            level.run();
        }
        // the fact that we reached to this part of the code testifying that the user win the game
        // so it's time to activate winningCase.
        animationRunner.winningCase(score, keyboardSensor);
    }
}

