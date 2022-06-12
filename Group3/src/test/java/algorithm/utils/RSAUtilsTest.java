package algorithm.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RSAUtilsTest {

    @Test
    void splitMessages() {
        List<String> messages = new ArrayList<String>();
        messages.add("message1");
        messages.add("message2");
        messages.add("message3");
        List<String> splitedMess = RSAUtils.splitMessages(messages);

        assertEquals(6, splitedMess.size());
        assertEquals("age1", splitedMess.get(1));
    }

    @Test
    void bigIntegerToString() {
        List<BigInteger> messages = new ArrayList<BigInteger>();
        messages.add(new BigInteger("Message".getBytes()));
        assertEquals("Message", RSAUtils.bigIntegerToString(messages));
        messages.add(new BigInteger(" number".getBytes()));
        assertEquals("Message number", RSAUtils.bigIntegerToString(messages));
    }

    @Test
    void bigIntegerSum() {
        List<BigInteger> nums = new ArrayList<BigInteger>();
        nums.add(new BigInteger("13"));
        nums.add(new BigInteger("25"));
        nums.add(new BigInteger("32"));

        assertEquals("70", RSAUtils.bigIntegerSum(nums));
    }
}