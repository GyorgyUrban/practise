package comparator;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import utility.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Stream;

import static comparator.Fraudulent.activityNotifications;
import static org.junit.Assert.assertEquals;
import static utility.Utility.readFile;

public class FraudulentTest {

    @Test
    public void test1() {
        int notifications = activityNotifications(new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 5);

        assertEquals(2, notifications);
    }

    @Test
    public void test2() throws IOException {

        String testCase2 = readFile("fraudulent_test_2.txt");
        int[] expenditure = Stream.of(testCase2.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int notifications = activityNotifications(expenditure, 10_000);

        assertEquals(633, notifications);
    }
}