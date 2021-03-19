package string;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonChildTest {

    @Test
    public void test1() {
        int lenght = CommonChild.commonChild("SHINCHAN", "NOHARAAA");

        assertEquals(3, lenght);
    }

    @Test
    public void test2() {
        int lenght = CommonChild.commonChild("HARRY", "SALLY");

        assertEquals(2, lenght);
    }

    @Test
    public void test3() {
        String s1 = "WEWOUCUIDGCGTRMEZEPXZFEJWISRSBBSYXAYDFEJJDLEBVHHKS";
        String s2 = "FDAGCXGKCTKWNECHMRXZWMLRYUCOCZHJRRJBOAJOQJZZVUYXIC";

        int lenght = CommonChild.commonChild(s1, s2);

        assertEquals(15, lenght);
    }
}