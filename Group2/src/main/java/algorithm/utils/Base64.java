package algorithm.utils;

import java.io.UnsupportedEncodingException;

public class Base64 {
    /*
     Розшифрує закодовані по Base64 вхідні дані і повертає їх у байтовому масиві.
    */
    public static byte[] decodeString(String str) {
        byte[] bytes = str.getBytes();
        return decode(bytes, bytes.length);
    }

    /*
     Розшифрує закодовані по Base64 вхідні дані і повертає їх у байтовому масиві.
    */
    private static byte[] decode(byte[] input, int len) {
        Decoder decoder = new Decoder(new byte[len*3/4]);

        if (!decoder.process(input, 0, len, true)) {
            throw new IllegalArgumentException("bad base-64");
        }

        if (decoder.op == decoder.output.length) {
            return decoder.output;
        }

        byte[] temp = new byte[decoder.op];
        System.arraycopy(decoder.output, 0, temp, 0, decoder.op);
        return temp;
    }

    /*
     Кодує по Base64 вхідні дані та повертає
     новостворену строку з результатом
    */
    public static String encodeToString(byte[] input) {

        try {
            return new String(encode(input, 0, input.length), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }


    /*
     Кодує по Base64 вхідні дані та повертає
     новостворену байт масив з результатом
    */
    private static byte[] encode(byte[] input, int offset, int len) {
        Encoder encoder = new Encoder(null);

        int output_len = len / 3 * 4;

        if (encoder.do_padding) {
            if (len % 3 > 0) {
                output_len += 4;
            }
        } else {
            switch (len % 3) {
                case 0: break;
                case 1: output_len += 2; break;
                case 2: output_len += 3; break;
            }
        }

        if (encoder.do_newline && len > 0) {
            output_len += (((len-1) / (3 * Encoder.LINE_GROUPS)) + 1) *
                    (encoder.do_cr ? 2 : 1);
        }

        encoder.output = new byte[output_len];
        encoder.process(input, offset, len, true);

        assert encoder.op == output_len;

        return encoder.output;
    }
}
