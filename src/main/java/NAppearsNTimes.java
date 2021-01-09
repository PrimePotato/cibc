import static java.lang.Math.sqrt;

public class NAppearsNTimes {
    public static int nthValue(int n) {
        return (int) ((1. + sqrt(1. + 8. * (n - 1))) / 2.);
    }

    public static int partialSum(int n) {
        int a = nthValue(n);
        int sn = ((6 * n + 1) * a - a * a * a);
        return sn / 6;
    }

    public static String questionOutput(int n) {
        return n + " " + partialSum(n);
    }
}
