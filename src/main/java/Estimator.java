

public enum Estimator {
    SECANT {
        @Override
        public int guess(double value, int leftIdx, int rightIdx, double[] a) {
            int mid = (int) Math.round((((value - a[leftIdx]) / (a[rightIdx] - a[leftIdx]))
                    * (rightIdx - leftIdx) + leftIdx));
            return Math.min(Math.max(mid, leftIdx), rightIdx);
        }
    },
    BISECT {
        @Override
        public int guess(double value, int leftIdx, int rightIdx, double[] a) {
            return leftIdx / 2 + rightIdx / 2;
        }
    };

    public abstract int guess(double value, int leftIdx, int rightIdx, double[] a);
}