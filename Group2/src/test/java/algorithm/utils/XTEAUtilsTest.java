package algorithm.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XTEAUtilsTest {
    private static XTEAUtils utils;

    @BeforeAll
    static void before(){
        utils = new XTEAUtils();
    }

    @Test
    void byteToInt() {
        String str = "test";
        byte[] bytes = str.getBytes();
        int[] ints = utils.byteToInt(bytes, 0);

        assertEquals(1, ints.length);
    }

    @Test
    void intToByte() {
        int[] ints = new int[1];
        ints[0] = 1952805748;
        byte[] bytes = utils.intToByte(ints);

        assertEquals(4, bytes.length);
    }

    @Test
    void toInt() {
        assertEquals(10, utils.toInt('a'));
    }

    @Test
    void replacePlus() {
        String str = "te+st";
        String replaced = utils.replacePlus(str);
        assertEquals("te%2Bst", replaced);
    }

    @Test
    void addPlus() {
        String str = "t%2Best";
        String added = utils.addPlus(str);
        assertEquals("t+est", added);
    }
}