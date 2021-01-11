import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EstimatorTest {

    private double[] exampleArray;

    @BeforeEach
    void setUp() {
        int size = 21;
        double[] ary = DoubleStream.generate(() -> 1. / size).limit(size).toArray();
        double sum = 0;
        double[] cs = new double[size];
        for (int i = 0; i < size; ++i) {
            sum += ary[i];
            cs[i] = sum;
        }
        this.exampleArray = cs;
    }

    @Test
    void guessBisect() {
        assertEquals(10, Estimator.BISECT.guess(0.25, 0, 20, this.exampleArray));
    }

    @Test
    void guessSecant() {
        assertEquals(4, Estimator.SECANT.guess(0.25, 0, 20, this.exampleArray));
    }
}