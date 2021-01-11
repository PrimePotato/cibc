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
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

//    @Test
//    void question2() {
//        System.out.println(NAppearsNTimes.questionOutput(1000));
//        System.out.println(NAppearsNTimes.questionOutput(1000));
//        System.out.println(NAppearsNTimes.questionOutput(1000));
//        System.out.println(NAppearsNTimes.questionOutput(1000));
//    }

    @Test
    void partialSum() {
        int[] nValues = {10, 6, 7, 11, 15, 16, 100, 10000, 1000, 21, 22};
        int[] expected = {30, 14, 18, 35, 55, 61, 945, 942820, 29820, 91, 98};
        int[] actual = Arrays.stream(nValues).map(NAppearsNTimes::partialSum).toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void questionOutput() {
    }

    @Test
    void questionOutputFile() {
        try {
            URL url = this.getClass().getResource("sampleInput.txt");
            Path path = Paths.get(url.toURI());
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.length() == 0) continue;
                if (line == "0") break;
                String[] s = line.split(" ");
                assertEquals(Integer.parseInt(s[3]), NAppearsNTimes.partialSum(Integer.parseInt(s[0])));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}