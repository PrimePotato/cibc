import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UniformDiscreteProbabilityDistributionTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void sizeMismatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UniformDiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, 0.7),
                    Arrays.asList(0.1, 0.2, 0.3, 0.4)
            );
        });
    }

    @Test
    void greaterThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UniformDiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, 1.3),
                    Arrays.asList(0.1, 0.2, 1.3)
            );
        });
    }

    @Test
    void lessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UniformDiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, -0.3, 0.7),
                    Arrays.asList(0.1, 0.2, -0.3, 0.7)
            );
        });
    }

    @Test
    void totalLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UniformDiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, 0.3),
                    Arrays.asList(0.1, 0.2, 0.3)
            );
        });
    }

    @Test
    void nextNum() {
        UniformDiscreteProbabilityDistribution<String> udpd = new UniformDiscreteProbabilityDistribution<>(
                Arrays.asList(0.1, 0.2, 0.3, 0.4),
                Arrays.asList("A", "B", "C", "D")
        );
        String nn = udpd.nextNum();
        System.out.println(nn);
    }

    @Test
    void verifyDistribution() {
        //add some verification
        UniformDiscreteProbabilityDistribution<String> udpd = new UniformDiscreteProbabilityDistribution<>(
                Arrays.asList(0.1, 0.2, 0.3, 0.4),
                Arrays.asList("A", "B", "C", "D")
        );
        Map<String, Integer> counter = new HashMap<>();
        int n = 100000000;
        for (int i = 0; i < n; i++) {
            String v = udpd.nextNum();
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        Map<String, Double> estProbs = counter.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (double) e.getValue() / (double) n));
        System.out.println(estProbs);
    }


}