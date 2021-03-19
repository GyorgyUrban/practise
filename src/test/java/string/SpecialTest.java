package string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static utility.Utility.readFile;

public class SpecialTest {

    @Test
    public void test1() {
        long count = Special.substrCount(7, "abcbaba");

        assertEquals(10, count);
    }

    @Test
    public void test2() {
        long count = Special.substrCount(727310, readFile("special_test_2.txt"));

        assertEquals(1272919, count);
    }
}