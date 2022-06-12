import algorithm.RSA;
import algorithm.impl.RSAImpl;
import algorithm.utils.RSAUtils;

import java.math.BigInteger;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("5700734181645378434561188374130529072194886062117");
        BigInteger q = new BigInteger("35894562752016259689151502540913447503526083241413");
        BigInteger e = new BigInteger("33445843524692047286771520482406772494816708076993");
        final String message = "Test message number 1";

        RSA RSA = new RSAImpl(p, q, e);
        System.out.println(RSA);

        System.out.println();
        List<BigInteger> decimalMessage = RSA.messageToDecimal(message);
        System.out.println("message(plain text)   = " + RSAUtils.bigIntegerToString(decimalMessage));
        System.out.println("message(decimal)      = " + RSAUtils.bigIntegerSum(decimalMessage));
        List<BigInteger> encryption = RSA.encryptMessage(message);
        System.out.println("encripted(decimal)    = " + RSAUtils.bigIntegerSum(encryption));
        List<BigInteger> signed = RSA.signMessage(message);


        List<BigInteger> decrypt = RSA.decryptMessages(encryption);
        List<BigInteger> verify = RSA.verify(signed);


        System.out.println("decrypted(plain text) = " + RSAUtils.bigIntegerToString(decrypt));
        System.out.println("decrypted(decimal)    = " + RSAUtils.bigIntegerSum(decrypt));
        System.out.println("signed(decimal)       = " + RSAUtils.bigIntegerSum(signed));
        System.out.println("verified(plain text)  = " + RSAUtils.bigIntegerToString(verify));
        System.out.println("verified(decimal)     = " + RSAUtils.bigIntegerSum(verify));
    }
}
