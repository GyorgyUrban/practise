package string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SherlockValidTest {

    @Test
    public void test1() {
        String result = SherlockValid.isValid("abcdefghhgfedecba");

        assertEquals("YES", result);
    }

    @Test
    public void test2() {
        String result = SherlockValid.isValid("aabbc");

        assertEquals("YES", result);
    }

    @Test
    public void test3() {
        String result = SherlockValid.isValid("aaaabbcc");

        assertEquals("NO", result);
    }
}