import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UniformDiscreteProbabilityDistributionTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void sizeMismatch() {
        assertThrows(IllegalArgumentException.class, ()-> {
            new UniformDiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, 0.7),
                    Arrays.asList(0.1, 0.2, 0.3, 0.4)
            );
        });
    }

    @Test
    void greaterThanOne() {
        assertThrows(IllegalArgumentException.class, ()-> {
            new UniformDiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, 1.3),
                    Arrays.asList(0.1, 0.2, 1.3)
            );
        });
    }

    @Test
    void lessThanZero() {
        assertThrows(IllegalArgumentException.class, ()-> {
            new UniformDiscreteProbabilityDistribution<>(
                    Arrays.asList(0.1, 0.2, -0.3, 0.7),
                    Arrays.asList(0.1, 0.2, -0.3, 0.7)
            );
        });
    }

    @Test
    void totalLessThanOne() {
        assertThrows(IllegalArgumentException.class, ()-> {
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



}