import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class UniformDiscreteProbabilityDistributionTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void greaterThanOne(){
        UniformDiscreteProbabilityDistribution<Double> udpd = new UniformDiscreteProbabilityDistribution<>(
                Arrays.asList(0.1, 0.2, 1.3),
                Arrays.asList(0.1, 0.2, 1.3)
        );

    }

}