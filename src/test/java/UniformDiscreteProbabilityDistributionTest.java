import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
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
    void quantile() {
        UniformDiscreteProbabilityDistribution<String> udpd = new UniformDiscreteProbabilityDistribution<>(
                Arrays.asList(0.1, 0.2, 0.3, 0.4),
                Arrays.asList("A", "B", "C", "D")
        );
        System.out.println(udpd.quantile(0.2));
        System.out.println(udpd.quantile(0.38));
        System.out.println(udpd.quantile(0.1));
        System.out.println(udpd.quantile(0.08));
        System.out.println(udpd.quantile(0.4));
    }

    @Test
    void verifyDistribution() {

        Map<String, Double> mp = Map.of("A", 0.1, "B", 0.2, "C", 0.3, "D", 0.4);
        UniformDiscreteProbabilityDistribution<String> udpd = new UniformDiscreteProbabilityDistribution<>(mp);

        Map<String, Integer> counter = new HashMap<>();
        int n = 10000;
        for (int i = 0; i < n; i++) {
            String v = udpd.nextNum();
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        Map<String, Double> estProbs = counter.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (double) e.getValue() / (double) n));

        for (Map.Entry<String, Double> e : mp.entrySet()){
            double diff = abs(estProbs.get(e.getKey()) - e.getValue());
            double se = UniformDiscreteProbabilityDistribution.sampleError(e.getValue(), n);
            assert diff < se * 5.0;
        }

        System.out.println(estProbs);
    }

    @Test
    void TimimgsForLargeDist(){
        // small a
        // large a
    }


}