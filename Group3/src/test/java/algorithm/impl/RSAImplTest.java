package algorithm.impl;

import algorithm.RSA;
import algorithm.utils.RSAUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RSAImplTest {
    private static RSA rsa;

    @BeforeAll
    static void before(){
        rsa = new RSAImpl(new BigInteger("101"), new BigInteger("113"), new BigInteger("3533"));
    }

    @Test
    void encrypt() {
        BigInteger number1 = new BigInteger("10");
        BigInteger number2 = new BigInteger("15");
        assertEquals(0, rsa.encrypt(number1).compareTo(new BigInteger("10514")));
        assertEquals(0, rsa.encrypt(number2).compareTo(new BigInteger("11315")));
    }

    @Test
    void encryptMessage() {
        String mess = "abc";
        List<BigInteger> encryption = rsa.encryptMessage(mess);

        assertEquals(3, encryption.size());
        assertEquals(0, encryption.get(0).compareTo(new BigInteger("290")));
    }

    @Test
    void decrypt() {
        BigInteger number1 = new BigInteger("10514");
        BigInteger number2 = new BigInteger("11315");
        assertEquals(0, rsa.decrypt(number1).compareTo(new BigInteger("10")));
        assertEquals(0, rsa.decrypt(number2).compareTo(new BigInteger("15")));
    }

    @Test
    void decryptMessages() {
        List<BigInteger> mess = new ArrayList<BigInteger>();
        mess.add(new BigInteger("290"));
        mess.add(new BigInteger("1454"));
        mess.add(new BigInteger("9661"));

        List<BigInteger> decryption = rsa.decryptMessages(mess);
        assertEquals("abc", RSAUtils.bigIntegerToString(decryption));
    }

    @Test
    void sign() {
        BigInteger number1 = new BigInteger("10");
        BigInteger number2 = new BigInteger("15");
        assertEquals(0, rsa.sign(number1).compareTo(new BigInteger("6474")));
        assertEquals(0, rsa.sign(number2).compareTo(new BigInteger("2614")));
    }

    @Test
    void signMessage() {
        String mess = "abc";
        List<BigInteger> signed = rsa.signMessage(mess);

        assertEquals(3, signed.size());
        assertEquals(0, signed.get(0).compareTo(new BigInteger("9464")));
    }

    @Test
    void verifySignedMessage() {
        BigInteger number1 = new BigInteger("6474");
        BigInteger number2 = new BigInteger("2614");

        assertEquals(0, rsa.verifySignedMessage(number1).compareTo(new BigInteger("10")));
        assertEquals(0, rsa.verifySignedMessage(number2).compareTo(new BigInteger("15")));
    }

    @Test
    void verify() {
        List<BigInteger> mess = new ArrayList<BigInteger>();
        mess.add(new BigInteger("9464"));
        mess.add(new BigInteger("11398"));
        mess.add(new BigInteger("1275"));

        List<BigInteger> verified = rsa.verify(mess);
        assertEquals("abc", RSAUtils.bigIntegerToString(verified));
    }

    @Test
    void isVerified() {
        BigInteger number1 = new BigInteger("5435");
        BigInteger number2 = new BigInteger("2614");
        assertEquals(false, rsa.isVerified(number1, new BigInteger("10")));
        assertEquals(true, rsa.isVerified(number2, new BigInteger("15")));
    }

    @Test
    void messageToDecimal() {
        String mess = "abcd";
        List<BigInteger> decimal = rsa.messageToDecimal(mess);

        assertEquals(4, decimal.size());
        assertEquals(0, decimal.get(3).compareTo(new BigInteger("100")));
    }

    @Test
    void testToString() {
        assertEquals("p                     = 101\n" +
                "q                     = 113\n" +
                "e                     = 3533\n" +
                "privateKey            = 6597\n" +
                "modulus               = 11413", rsa.toString());
    }
}