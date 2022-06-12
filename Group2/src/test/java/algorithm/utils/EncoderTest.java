package algorithm.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {
    private static Encoder encoder;

    @BeforeAll
    static void before(){
        encoder = new Encoder(new byte[20]);
    }

    @Test
    void maxOutputSize() {
        assertEquals(26, encoder.maxOutputSize(10));
        assertEquals(34, encoder.maxOutputSize(15));
    }

    @Test
    void process() {
        String str = "Testing";
        byte[] bytes = str.getBytes();
        boolean result = encoder.process(bytes, 0, bytes.length, true);
        assertEquals(true, result);
    }
}