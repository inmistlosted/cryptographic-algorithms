package algorithms;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class KaratsubaMultiplicationTest {

    @Test
    void multiply() {
        BigInteger mult1 = KaratsubaMultiplication.multiply(new BigInteger("8793458935"), new BigInteger("23425980752276572651"));
        BigInteger mult2 = KaratsubaMultiplication.multiply(new BigInteger("9808304859308503985"), new BigInteger("765836836583758368198676324"));

        assertEquals(0, KaratsubaMultiplication.multiply(BigInteger.ZERO, BigInteger.ONE).compareTo(BigInteger.ZERO));
        assertEquals(0, mult1.compareTo(new BigInteger("205995399757244449369112586685")));
        assertEquals(0, mult2.compareTo(new BigInteger("7511561165701929879230871308923847233679151140")));
    }
}