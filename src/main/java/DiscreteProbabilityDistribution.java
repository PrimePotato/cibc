import java.util.*;

@SuppressWarnings("unchecked")
public class DiscreteProbabilityDistribution<T> {
    private final double[] probabilities;
    private final double[] cumulativeProbabilities;
    private final T[] labels;

    private final double tol = 1e-16;
    private Random random = new Random();

    DiscreteProbabilityDistribution(double[] probabilities, T[] labels) {
        this.probabilities = probabilities;
        this.labels = labels;
        checkSizes();
        checkLabelsUnique();
        checkValidProbabilities();
        checkTotalProbability();
        this.cumulativeProbabilities = cumulativeProbabilities();
    }

    DiscreteProbabilityDistribution(List<Double> probabilities, List<T> labels) {
        this(
                probabilities.stream().mapToDouble(Double::doubleValue).toArray(),
                (T[]) labels.toArray()
        );
    }

    DiscreteProbabilityDistribution(Map<T, Double> mapProbabilities) {
        this(
                mapProbabilities.values().stream().mapToDouble(Double::doubleValue).toArray(),
                (T[]) mapProbabilities.keySet().toArray()
        );
    }


    public T nextNum(Estimator estimator) {
        return quantile(random.nextFloat(), estimator);
    }

    public T nextNum() {
        return quantile(random.nextFloat(), Estimator.SECANT);
    }


    public static double sampleError(double p, double n) {
        return Math.sqrt((p * (1 - p)) / n);
    }

    public static int searchLeft(double value, double[] a, Estimator estimator) {
        if (value < a[0]) return 0;
        if (value > a[a.length - 1]) return a.length - 1;

        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = estimator.guess(value, left, right, a);
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

    public T quantile(double p, Estimator estimator) {
        return this.labels[searchLeft(p, this.cumulativeProbabilities, estimator)];
    }

    private double[] cumulativeProbabilities() {
        double tot = 0.;
        int n = this.probabilities.length;
        double[] cp = new double[n];
        for (int i = 0; i < n; i++) {
            tot += this.probabilities[i];
            cp[i] = tot;
        }
        return cp;
    }


    private void checkSizes() {
        if (this.labels.length != this.probabilities.length) {
            throw new IllegalArgumentException("Lists must be the same size");
        }
    }

    private void checkTotalProbability() {
        if ((1 - Arrays.stream(this.probabilities).reduce(0., Double::sum)) > tol * this.probabilities.length) {
            throw new IllegalArgumentException("Probabilities do not sum to 1");
        }
    }

    private void checkLabelsUnique() {
        Set<T> ls = new HashSet<T>();
        Collections.addAll(ls, this.labels);
        if (ls.size() != this.labels.length) {
            throw new IllegalArgumentException("Labels not unique");
        }
    }

    private void checkValidProbabilities() {
        Arrays.stream(this.probabilities).forEach(p -> {
            if (p < 0) throw new IllegalArgumentException("The probability " + p + " is less than 0");
            if (p > 1) throw new IllegalArgumentException("The probability " + p + " is greater than 1");
        });
    }


}
