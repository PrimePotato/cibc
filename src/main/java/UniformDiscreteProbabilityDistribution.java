import java.util.List;
import java.util.Random;


public class UniformDiscreteProbabilityDistribution<T> {
    private final List<Double> probabilities;
    private final double[] cumulativeProbabilities;
    private final List<T> values;

    private double tol = 1e-14;
    private Random random = new Random();

    UniformDiscreteProbabilityDistribution(List<Double> probabilities, List<T> values) {
        this.probabilities = probabilities;
        this.values = values;
        checkSizes();
        checkValidProbabilities();
        checkTotalProbability();
        this.cumulativeProbabilities = cumulativeProbabilities();
    }

    private double[] cumulativeProbabilities() {
        double tot = 0.;
        int n = this.probabilities.size();
        double[] cp = new double[n];
        for (int i = 0; i < n; i++) {
            tot += this.probabilities.get(i);
            cp[i] = tot;
        }
        return cp;
    }

    public static double sampleError(int p, int n) {
        return Math.sqrt((p * (1 - p)) / (double) n);
    }

    public int searchLeft(double value, double[] a, Estimator estimator) {
        if (value < a[0]) return 0;
        if (value > a[a.length - 1]) return a.length - 1;

        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = estimator.guess(value, left, right, a);
            mid = Math.max(mid, left);
            mid = Math.min(mid, right);
            if (value < a[mid]) {
                right = mid - 1;
            } else if (value > a[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public T quantile(double p) {
        return this.values.get(searchLeft(p, this.cumulativeProbabilities, Estimator.BISECT));
    }

    private void checkSizes() {
        if (this.values.size() != this.probabilities.size()) {
            throw new IllegalArgumentException("Lists must be the same size");
        }
    }

    private void checkTotalProbability() {
        if ((1 - this.probabilities.stream().reduce(0., Double::sum)) > tol) {
            throw new IllegalArgumentException("Probabilities do not sum to 1");
        }
    }

    private void checkValidProbabilities() {
        probabilities.forEach(p -> {
            if (p < 0) throw new IllegalArgumentException("The probability " + p + " is less than 0");
            if (p > 1) throw new IllegalArgumentException("The probability " + p + " is greater than 1");
        });
    }

    public T nextNum() {
        return quantile(random.nextFloat());
    }
}
