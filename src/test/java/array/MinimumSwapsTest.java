package array;

import array.MinimumSwaps;
import org.junit.jupiter.api.Test;

public class MinimumSwapsTest {
    @Test
    void test1() {
        System.out.println(MinimumSwaps.minimumSwaps(new int[]{4, 3, 1, 2}));
    }

    @Test
    void test2() {
        System.out.println(MinimumSwaps.minimumSwaps(new int[]{2, 3, 4, 1, 5}));
    }
}