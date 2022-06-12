package algorithm.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base64Test {
    @Test
    void decodeString() {
        String strToDecode  = "dGVzdA";
        byte[] bytes = Base64.decodeString(strToDecode);
        assertEquals(4, bytes.length);
        assertEquals("test", new String(bytes));
    }

    @Test
    void encodeToString() {
        String str = "test";
        byte[] bytes = str.getBytes();
        String encodedStr = Base64.encodeToString(bytes);
        assertEquals("dGVzdA", encodedStr);
    }
}