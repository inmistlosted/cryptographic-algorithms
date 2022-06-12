package algorithms;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BinaryExponentiationTest {
    @Test
    void pow() {
        BigInteger exp1 = BinaryExponentiation.pow(new BigInteger("87589375345132432447657"), BigInteger.ZERO);
        BigInteger exp2 = BinaryExponentiation.pow(BigInteger.ONE, new BigInteger("140942094029402"));
        BigInteger exp3 = BinaryExponentiation.pow(new BigInteger("2"), new BigInteger("10"));

        assertEquals(0, exp1.compareTo(BigInteger.ONE));
        assertEquals(0, exp2.compareTo(BigInteger.ONE));
        assertEquals(0, exp3.compareTo(new BigInteger("1024")));
    }
}