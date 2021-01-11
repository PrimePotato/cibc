import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

class DiscreteProbabilityDistributionTest {

    @Test
    void sizeMismatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, 0.7),
                    Arrays.asList(0.1, 0.2, 0.3, 0.4)
            );
        });
    }

    @Test
    void greaterThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, 1.3),
                    Arrays.asList(0.1, 0.2, 1.3)
            );
        });
    }

    @Test
    void lessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, -0.3, 0.7),
                    Arrays.asList(0.1, 0.2, -0.3, 0.7)
            );
        });
    }

    @Test
    void totalLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, 0.3),
                    Arrays.asList(0.1, 0.2, 0.3)
            );
        });
    }

    @Test
    void nextNum() {
        DiscreteProbabilityDistribution<String> udpd = new DiscreteProbabilityDistribution<>(
                Arrays.asList(0.1, 0.2, 0.3, 0.4),
                Arrays.asList("A", "B", "C", "D")
        );
        String nn = udpd.nextNum();
        assertNotNull(nn);
        System.out.println(nn);
    }

    @Test
    void quantile() {
        DiscreteProbabilityDistribution<String> udpd = new DiscreteProbabilityDistribution<>(
                Arrays.asList(0.1, 0.2, 0.3, 0.4),
                Arrays.asList("A", "B", "C", "D")
        );
        assertEquals("B", udpd.quantile(0.2, Estimator.BISECT));
        assertEquals("C", udpd.quantile(0.38, Estimator.BISECT));
        assertEquals("A", udpd.quantile(0.1, Estimator.BISECT));
        assertEquals("A", udpd.quantile(0.08, Estimator.BISECT));
        assertEquals("C", udpd.quantile(0.5, Estimator.BISECT));
        assertEquals("D", udpd.quantile(0.8, Estimator.BISECT));
    }

    private void verifyDistribution(int n, Estimator estimator){
        Map<String, Double> mp = Map.of("A", 0.1, "B", 0.2, "C", 0.3, "D", 0.4);
        DiscreteProbabilityDistribution<String> udpd = new DiscreteProbabilityDistribution<>(mp);

        Map<String, Integer> counter = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            String v = udpd.nextNum(estimator);
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        Map<String, Double> estProbs = counter.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, e -> (double) e.getValue() / (double) n));

        for (Map.Entry<String, Double> e : mp.entrySet()) {
            double diff = abs(estProbs.get(e.getKey()) - e.getValue());
            double se = DiscreteProbabilityDistribution.sampleError(e.getValue(), n);
            assertTrue(diff < se * 5.0);
        }

        System.out.println(estProbs);
    }

    @Test
    void verifyDistributions() {
        verifyDistribution(10000000, Estimator.BISECT);
        verifyDistribution(10000000, Estimator.SECANT);
    }

    private void speedTestUniform(int size, int n, Estimator estimator) {
        double[] ps = DoubleStream.generate(() -> 1. / size).limit(size).toArray();
        Integer[] vs = IntStream.range(0, size).boxed().toArray(Integer[]::new);
        DiscreteProbabilityDistribution<Integer> udpd = new DiscreteProbabilityDistribution<>(ps, vs);
        long before = System.currentTimeMillis();
        IntStream.range(0, n).forEach(i -> udpd.nextNum(estimator));
        long after = System.currentTimeMillis();
        System.out.println("Dist: Uniform Results: Time(ms): " + (after - before) + " Method: " + estimator + " n: " + n + " size:" + size);
    }

    private void speedTestRandom(int size, int n, Estimator estimator) {
        Random rnd = new Random();
        double[] ps = DoubleStream.generate(() -> rnd.nextFloat()).limit(size).toArray();
        double total = Arrays.stream(ps).sum();
        ps = Arrays.stream(ps).map(d -> d / total).toArray();
        Integer[] vs = IntStream.range(0, size).boxed().toArray(Integer[]::new);
        DiscreteProbabilityDistribution<Integer> udpd = new DiscreteProbabilityDistribution<>(ps, vs);
        long before = System.currentTimeMillis();
        IntStream.range(0, n).forEach(i -> udpd.nextNum(estimator));
        long after = System.currentTimeMillis();
        System.out.println("Dist: Random Results: Time(ms): " + (after - before) + " Method: " + estimator + " n: " + n + " size:" + size);
    }

    @Test
    void speedComparison() {

        speedTestUniform(10, 10000000, Estimator.BISECT);
        speedTestUniform(10, 10000000, Estimator.SECANT);

        speedTestUniform(100000, 10000000, Estimator.BISECT);
        speedTestUniform(100000, 10000000, Estimator.SECANT);

        speedTestRandom(10, 10000000, Estimator.BISECT);
        speedTestRandom(10, 10000000, Estimator.SECANT);

        speedTestRandom(100000, 10000000, Estimator.BISECT);
        speedTestRandom(100000, 10000000, Estimator.SECANT);
    }
}