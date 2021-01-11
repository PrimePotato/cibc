import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.lang.Math.sqrt;

public class NAppearsNTimes {
    public static int nthValue(int n) {
        return (int) (0.5 + sqrt(2 * n));
    }

    public static int partialSum(int n) {
        int a = nthValue(n);
        return ((6 * n + 1) * a - a * a * a) / 6;
    }

    public static void verifySampleTestDate(Path path) {
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
}
