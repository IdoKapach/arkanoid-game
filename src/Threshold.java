// 322384561 - IDO KAPACH
/**
 * The type Threshold checks if two doubles are close enough to be calculated as equal.
 */
public class Threshold {
    /**
     * The constant EPCILON - the max "level of close" between the two doubles thar enough to be calculated as equal.
     */
    public static final double EPCILON = 0.001;

    /**
     * checks if two doubles are close enough (more than epcilon or epcilon) to be calculated as equal.
     *
     * @param x1 (double) the first double
     * @param x2 (double) the second double
     * @return (int) returns 0 if x1 "=" x2, returns 1 if x1>x2. otherwise, it returns -1.
     */
    public int haveInteraction(double x1, double x2) {
        if (x1 - x2 <= EPCILON && x2 - x1 <= EPCILON) {
            return 0;
        }
        return x1 > x2 ? 1 : -1;
    }
}
