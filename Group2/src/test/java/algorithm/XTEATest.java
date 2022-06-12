package algorithm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class XTEATest {
    private static XTEA xtea;

    @BeforeAll
    static void before(){
        xtea = new XTEA();
    }

    @Test
    void encryptByBase64XTEA() throws UnsupportedEncodingException {
        String str = "abc";
        String encryptedStr = xtea.encryptByBase64XTEA(str);
        assertEquals("JPXKgIHMcTg", encryptedStr);
    }

    @Test
    void decryptByBase64XTEA() {
        String encryptedStr = "JPXKgIHMcTg";
        String decryptedStr = xtea.decryptByBase64XTEA(encryptedStr);
        assertEquals("abc", decryptedStr);
    }
}