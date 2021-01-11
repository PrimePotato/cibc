import static java.lang.Math.sqrt;

public class NAppearsNTimes {
    public static long nthValue(long n) {
        return (long) (0.5 + sqrt(2 * n));
    }

    public static long partialSum(long n) {
        long a = nthValue(n);
        return ((6 * n + 1) * a - a * a * a) / 6;
    }
}
