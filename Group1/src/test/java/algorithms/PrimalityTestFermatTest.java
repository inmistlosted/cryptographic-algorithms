package algorithms;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class PrimalityTestFermatTest {

    @Test
    void isPrime() {
        BigInteger[] primeList = new BigInteger[]{
                new BigInteger("5"),
                new BigInteger("23"),
                new BigInteger("31"),
                new BigInteger("43"),
                new BigInteger("73"),
                new BigInteger("79"),
                new BigInteger("101"),
                new BigInteger("113"),
                new BigInteger("999946346551"),
                new BigInteger("999946346563"),
                new BigInteger("999946346723"),
                new BigInteger("999946346813"),
        };

        BigInteger[] nonPrimeList = new BigInteger[]{
                new BigInteger("8"),
                new BigInteger("25"),
                new BigInteger("36"),
                new BigInteger("49"),
                new BigInteger("72"),
                new BigInteger("95"),
                new BigInteger("100"),
                new BigInteger("121"),
                new BigInteger("999946346555"),
                new BigInteger("999946346562"),
                new BigInteger("999946346721"),
                new BigInteger("999946346816"),
        };

        for (BigInteger bigInteger : primeList) {
            assertTrue(PrimalityTestFermat.isPrime(bigInteger));
        }

        for (BigInteger bigInteger : nonPrimeList) {
            assertFalse(PrimalityTestFermat.isPrime(bigInteger));
        }
    }
}