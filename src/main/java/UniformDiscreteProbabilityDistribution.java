import java.util.ArrayList;
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

    private T quantile(double p) {
        for (int i = 0; i < this.cumulativeProbabilities.length; i++) {
            if (this.cumulativeProbabilities[i] > p) {
                return this.values.get(i);
            }
        }
        return null;
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
