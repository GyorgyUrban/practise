package hashmap;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import utility.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static utility.Utility.readFile;

public class TripletsTest {


    @Test
    public void test1() {
        List<Long> list = new ArrayList<>();
        LongStream.range(0, 100).forEach(value -> list.add(1l));
        long l = Triplets.countTriplets(list, 1);
        System.out.println(l);
    }

    @Test
    public void test2() {
        String testCase2 = readFile("triple_test_2.txt");

        long l = Triplets.countTriplets(Stream.of(testCase2.split(",")).map(Long::parseLong).collect(Collectors.toList()), 3);
        System.out.println(l);
    }
}