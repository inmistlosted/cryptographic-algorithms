import algorithm.XTEA;

import java.io.UnsupportedEncodingException;

public class Main {
        public static void main(String[] args) throws UnsupportedEncodingException {
            XTEA xtea = new XTEA();
            String message = "Test message to test algorithm";

            String encrypted = xtea.encryptByBase64XTEA(message);
            System.out.println(encrypted);
            String decrypted = xtea.decryptByBase64XTEA(encrypted);
            System.out.println(decrypted);
    }
}
