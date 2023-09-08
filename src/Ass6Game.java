// 322384561 - IDO KAPACH

import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game runs the game.
 */
public class Ass6Game {
    /*
    this func creates list of levels for the game. The composition of this list is determined by the arguments
     in args.
     */
    private static List<LevelInformation> createOrder(String[] args) {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length > 0) {
            for (String arg : args) {
                // if the arg = 1/2/3, adding to the levels the appropriate level.
                switch (arg) {
                    case "1":
                        levels.add(new Level1());
                        continue;
                    case "2":
                        levels.add(new Level2());
                        continue;
                    case "3":
                        levels.add(new Level3());
                        continue;
                    default:
                        continue;
                }
            }
        }
        // default combination of levels - if the list levels is still empty.
        if (levels.isEmpty()) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
        }
        return levels;
    }
    /**
     * The entry point of application. runs the game of ass6.
     *
     * @param args the input arguments.
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner();
        KeyboardSensor keyboardSensor = runner.getGui().getKeyboardSensor();
        // gets the list of the levels from createOrder.
        List<LevelInformation> levels = createOrder(args);
        // runs the game.
        GameFlow game = new GameFlow(runner, keyboardSensor);
        game.runLevels(levels);
    }
}
