import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NAppearsNTimesTest {

    @Test
    void nthValue() {
        long[] nValues = {1, 2, 3, 4, 5, 6, 10, 100, 1000};
        long[] expected = {1, 2, 2, 3, 3, 3, 4, 14, 45};
        long[] actual = Arrays.stream(nValues).map(NAppearsNTimes::nthValue).toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void visualInspection() {
        for (long n : LongStream.range(1, 20).toArray()) {
            System.out.println(n + " " + NAppearsNTimes.nthValue(n) + " " + NAppearsNTimes.partialSum(n));
        }
    }

    @Test
    void partialSum() {
        long[] nValues = {10, 6, 7, 11, 15, 16, 100, 10000, 1000, 21, 22};
        long[] expected = {30, 14, 18, 35, 55, 61, 945, 942820, 29820, 91, 98};
        long[] actual = Arrays.stream(nValues).map(NAppearsNTimes::partialSum).toArray();
        assertArrayEquals(expected, actual);
    }

    private static void verifySampleTestDate(Path path) {
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.length() == 0) continue;
                if (line.charAt(0) == '0') break;
                String[] s = line.split(" ");
                assert Integer.parseInt(s[3]) == NAppearsNTimes.partialSum(Integer.parseInt(s[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    @Test
    void sampleInputFile() {
        URL url = this.getClass().getResource("sampleInput.txt");
        try {
            Path path = Paths.get(url.toURI());
            verifySampleTestDate(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}