package algorithm.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {
    private static Decoder decoder;

    @BeforeAll
    static void before(){
        decoder = new Decoder(new byte[20]);
    }

    @Test
    void maxOutputSize() {
        assertEquals(17, decoder.maxOutputSize(10));
        assertEquals(21, decoder.maxOutputSize(15));
    }

    @Test
    void process() {
        String str = "Hello world";
        byte[] bytes = str.getBytes();
        boolean result = decoder.process(bytes, 0, bytes.length, true);
        assertEquals(true, result);
    }
}