import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NAppearsNTimesTest {

    @Test
    void nthValue() {
        int[] nValues = {1, 2, 3, 4, 5, 6, 10, 100, 1000};
        int[] expected = {1, 2, 2, 3, 3, 3, 4, 14, 45};
        int[] actual = Arrays.stream(nValues).map(NAppearsNTimes::nthValue).toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void visualInspection() {
        for (int n : IntStream.range(1, 20).toArray()) {
            System.out.println(n + " " + NAppearsNTimes.nthValue(n) + " " + NAppearsNTimes.partialSum(n));
        }
    }

    @Test
    void partialSum() {
        int[] nValues = {10, 6, 7, 11, 15, 16, 100, 10000, 1000, 21, 22};
        int[] expected = {30, 14, 18, 35, 55, 61, 945, 942820, 29820, 91, 98};
        int[] actual = Arrays.stream(nValues).map(NAppearsNTimes::partialSum).toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void sampleInputFile() {
        URL url = this.getClass().getResource("sampleInput.txt");
        try {
            Path path = Paths.get(url.toURI());
            NAppearsNTimes.verifySampleTestDate(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}