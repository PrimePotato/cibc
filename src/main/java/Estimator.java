

public enum Estimator {
    SECANT {
        @Override
        public int guess(double value, int leftIdx, int rightIdx, double[] a) {
            int mid = (int) (((value - a[leftIdx]) / (a[rightIdx] - a[leftIdx])) * (rightIdx - leftIdx) + leftIdx);
            mid = Math.max(mid, leftIdx);
            mid = Math.min(mid, rightIdx);
            return mid;
        }
    },
    BISECT{
        @Override
        public int guess(double value, int leftIdx, int rightIdx, double[] a) {
            return (leftIdx + rightIdx) / 2;
        }
    };
    public abstract int guess(double value, int leftIdx, int rightIdx, double[] a);
}