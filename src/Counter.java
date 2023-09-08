// 322384561 - IDO KAPACH
/**
 * The type Counter.
 */
public class Counter {
    private int num = 0;

    /**
     * Instantiates a new Counter - default constructor.
     */
    public Counter() {
        return;
    }

    /**
     * Instantiates a new Counter.
     *
     * @param num {int} the starting value of counting.
     */
    public Counter(int num) {
        this.num = num;
    }

    /**
     * Increase the num fields by given number.
     *
     * @param number {int} the given number
     */
    public void increase(int number) {
        num += number;
    }

    /**
     * Decrease the num fields by given number.
     *
     * @param number {int} the given number
     */
    public void decrease(int number) {
        num -= number;
    }

    /**
     * get current count.
     *
     * @return {int} the value.
     */

    public int getValue() {
        return num;
    }
}
