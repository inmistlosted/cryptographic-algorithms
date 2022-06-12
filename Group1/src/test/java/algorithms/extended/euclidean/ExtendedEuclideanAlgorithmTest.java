package algorithms.extended.euclidean;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedEuclideanAlgorithmTest {

    @Test
    void calculateGcd() {
        BezoutIdentity i1 = ExtendedEuclideanAlgorithm.calculateGcd(new BigInteger("30"), new BigInteger("20"));
        BezoutIdentity i2 = ExtendedEuclideanAlgorithm.calculateGcd(new BigInteger("35"), new BigInteger("15"));

        assertEquals(0, i1.getGcd().compareTo(new BigInteger("10")));
        assertEquals(0, i1.getX().compareTo(new BigInteger("1")));
        assertEquals(0, i1.getY().compareTo(new BigInteger("-1")));
        assertEquals(0, i2.getGcd().compareTo(new BigInteger("5")));
        assertEquals(0, i2.getX().compareTo(new BigInteger("1")));
        assertEquals(0, i2.getY().compareTo(new BigInteger("-2")));
    }
}