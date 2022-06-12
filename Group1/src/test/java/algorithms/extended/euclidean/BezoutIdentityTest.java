package algorithms.extended.euclidean;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BezoutIdentityTest {
    @Test
    void constructorTest(){
        BigInteger i1 = new BigInteger("3453535");
        BigInteger i2 = new BigInteger("89656546");
        BigInteger i3 = new BigInteger("12376752432");
        BigInteger i4 = new BigInteger("875467567425");
        BigInteger i5 = new BigInteger("5346364636");

        BezoutIdentity identity = new BezoutIdentity(i1, i2, i3, i4, i5);

        assertEquals(0, identity.getA().compareTo(i1));
        assertEquals(0, identity.getB().compareTo(i2));
        assertEquals(0, identity.getX().compareTo(i3));
        assertEquals(0, identity.getY().compareTo(i4));
        assertEquals(0, identity.getGcd().compareTo(i5));
    }

    @Test
    void methodTest(){
        BigInteger i1 = new BigInteger("3453535");
        BigInteger i2 = new BigInteger("89656546");
        BigInteger i3 = new BigInteger("12376752432");
        BigInteger i4 = new BigInteger("875467567425");
        BigInteger i5 = new BigInteger("5346364636");

        BezoutIdentity identity = new BezoutIdentity();
        identity.setA(i1);
        identity.setB(i2);
        identity.setX(i3);
        identity.setY(i4);
        identity.setGcd(i5);

        assertEquals(0, identity.getA().compareTo(i1));
        assertEquals(0, identity.getB().compareTo(i2));
        assertEquals(0, identity.getX().compareTo(i3));
        assertEquals(0, identity.getY().compareTo(i4));
        assertEquals(0, identity.getGcd().compareTo(i5));
    }

}