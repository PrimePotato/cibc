import static java.lang.Math.sqrt;

public class NAppearsNTimes {
    public static int nthValue(int n) {
        return (int) (0.5 + sqrt(2 * n));
    }

    public static int partialSum(int n) {
        int a = nthValue(n);
        return ((6 * n + 1) * a - a * a * a) / 6;
    }

    public static String questionOutput(int n) {
        return n + " " + partialSum(n);
    }
}
