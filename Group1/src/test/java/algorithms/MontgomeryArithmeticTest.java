package algorithms;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MontgomeryArithmeticTest {

    @Test
    void multiply() {
        BigInteger num1 = MontgomeryArithmetic.multiply(new BigInteger("869759074587927434"), new BigInteger("90887357987345534"), new BigInteger("53537635435"));
        BigInteger num2 = MontgomeryArithmetic.multiply(new BigInteger("234234244"), new BigInteger("1473468392"), new BigInteger("9853453"));

        assertEquals(0, num1.compareTo(new BigInteger("13337745626")));
        assertEquals(0, num2.compareTo(new BigInteger("4610251")));
    }

    @Test
    void pow() {
        BigInteger num1 = MontgomeryArithmetic.pow(new BigInteger("869759074587927434"), new BigInteger("2"), new BigInteger("53537635435"));
        BigInteger num2 = MontgomeryArithmetic.multiply(new BigInteger("234234244"), new BigInteger("7"), new BigInteger("9853453"));

        assertEquals(0, num1.compareTo(new BigInteger("38068721511")));
        assertEquals(0, num2.compareTo(new BigInteger("3966510")));
    }
}