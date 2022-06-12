package algorithm.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSAUtils {
    public static List<String> splitMessages(List<String> messages) {
        List<String> splitedMessages = new ArrayList<String>(messages.size() * 2);
        for (String message : messages) {
            int half = (int) Math.ceil(((double) message.length()) / ((double) 2));
            splitedMessages.add(message.substring(0, half));
            if (half < message.length()) {
                splitedMessages.add(message.substring(half));
            }
        }

        return splitedMessages;
    }

    public static String bigIntegerToString(List<BigInteger> list) {
        StringBuilder plainText = new StringBuilder();
        for (BigInteger bigInteger : list) {
            plainText.append(new String(bigInteger.toByteArray()));
        }
        return plainText.toString();
    }

    public static String bigIntegerSum(List<BigInteger> list) {
        BigInteger result = BigInteger.ZERO;
        for (BigInteger bigInteger : list) {
            result = result.add(bigInteger);
        }
        return result.toString();
    }
}
