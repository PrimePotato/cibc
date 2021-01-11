import static java.lang.Math.sqrt;

public class NAppearsNTimes {
    public static final long  MAX_VALUE = 1385297844439L;

    public static long nthValue(long n) {
        return (long) (0.5 + sqrt(2 * n));
    }

    public static long partialSum(long n) {
        if (n > MAX_VALUE){
            throw new IllegalArgumentException("Value of n is too high must be less than " + MAX_VALUE);
        }
        return uncheckedPartialSum(n);
    }

    public static long uncheckedPartialSum(long n) {
        long a = nthValue(n);
        return ((6 * n + 1) * a - a * a * a) / 6;
    }

    public static long findMaxValue() {
        long left = Long.MAX_VALUE/(long)(1e7);;
        long right = Long.MAX_VALUE/(long)(1e6);

        while (left <= right) {
            long mid = left / 2 + right / 2;
            long val = NAppearsNTimes.uncheckedPartialSum(mid) ;
            if (val > 0) {
                left = mid + 1;
            } else if (val < 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return Math.min(left, right);
    }

}
