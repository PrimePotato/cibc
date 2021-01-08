import java.util.List;
import java.util.Random;


public class UniformDiscreteProbabilityDistribution<T> {
    private final List<Double> probabilities;
    private final List<T> values;

    private double tol = 1e-14;
    private Random random = new Random();

    UniformDiscreteProbabilityDistribution(List<Double> probabilities, List<T> values) {
        this.probabilities = probabilities;
        this.values = values;
        checkValidProbabilities();
        checkTotalProbability();
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

    public double nextNum() {
        return random.nextFloat();
    }
}
