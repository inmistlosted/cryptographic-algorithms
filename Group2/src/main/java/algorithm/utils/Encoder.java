package algorithm.utils;

public class Encoder extends Coder {
    public static final int LINE_GROUPS = 19;

    /*
     Таблиця пошуку для перетворення позицій алфавіту Base64 у вихідні байти.
    */
    private static final byte[] ENCODE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/',
    };

    final private byte[] tail;
    private int tailLen;
    private int count;

    final public boolean do_padding;
    final public boolean do_newline;
    final public boolean do_cr;
    final private byte[] alphabet;

    public Encoder(byte[] output) {
        this.output = output;

        do_padding = false;
        do_newline = false;
        do_cr = true;
        alphabet = ENCODE;

        tail = new byte[2];
        tailLen = 0;

        count = -1;
    }

    public int maxOutputSize(int len) {
        return len * 8/5 + 10;
    }

    public boolean process(byte[] input, int offset, int len, boolean finish) {
        final byte[] alphabet = this.alphabet;
        final byte[] output = this.output;
        int op = 0;
        int count = this.count;

        int p = offset;
        len += offset;
        int v = -1;

        switch (tailLen) {
            case 0:
                break;
            case 1:
                if (p+2 <= len) {
                    v = ((tail[0] & 0xff) << 16) |
                            ((input[p++] & 0xff) << 8) |
                            (input[p++] & 0xff);
                    tailLen = 0;
                };
                break;

            case 2:
                if (p+1 <= len) {
                    v = ((tail[0] & 0xff) << 16) |
                            ((tail[1] & 0xff) << 8) |
                            (input[p++] & 0xff);
                    tailLen = 0;
                }
                break;
        }

        if (v != -1) {
            output[op++] = alphabet[(v >> 18) & 0x3f];
            output[op++] = alphabet[(v >> 12) & 0x3f];
            output[op++] = alphabet[(v >> 6) & 0x3f];
            output[op++] = alphabet[v & 0x3f];
            if (--count == 0) {
                if (do_cr) output[op++] = '\r';
                output[op++] = '\n';
                count = LINE_GROUPS;
            }
        }

        while (p+3 <= len) {
            v = ((input[p] & 0xff) << 16) |
                    ((input[p+1] & 0xff) << 8) |
                    (input[p+2] & 0xff);
            output[op] = alphabet[(v >> 18) & 0x3f];
            output[op+1] = alphabet[(v >> 12) & 0x3f];
            output[op+2] = alphabet[(v >> 6) & 0x3f];
            output[op+3] = alphabet[v & 0x3f];
            p += 3;
            op += 4;
            if (--count == 0) {
                if (do_cr) output[op++] = '\r';
                output[op++] = '\n';
                count = LINE_GROUPS;
            }
        }

        if (finish) {
            if (p-tailLen == len-1) {
                int t = 0;
                v = ((tailLen > 0 ? tail[t++] : input[p++]) & 0xff) << 4;
                tailLen -= t;
                output[op++] = alphabet[(v >> 6) & 0x3f];
                output[op++] = alphabet[v & 0x3f];
                if (do_padding) {
                    output[op++] = '=';
                    output[op++] = '=';
                }
                if (do_newline) {
                    if (do_cr) output[op++] = '\r';
                    output[op++] = '\n';
                }
            } else if (p-tailLen == len-2) {
                int t = 0;
                v = (((tailLen > 1 ? tail[t++] : input[p++]) & 0xff) << 10) |
                        (((tailLen > 0 ? tail[t++] : input[p++]) & 0xff) << 2);
                tailLen -= t;
                output[op++] = alphabet[(v >> 12) & 0x3f];
                output[op++] = alphabet[(v >> 6) & 0x3f];
                output[op++] = alphabet[v & 0x3f];
                if (do_padding) {
                    output[op++] = '=';
                }
                if (do_newline) {
                    if (do_cr) output[op++] = '\r';
                    output[op++] = '\n';
                }
            } else if (do_newline && op > 0 && count != LINE_GROUPS) {
                if (do_cr) output[op++] = '\r';
                output[op++] = '\n';
            }

            assert tailLen == 0;
            assert p == len;
        } else {
            if (p == len-1) {
                tail[tailLen++] = input[p];
            } else if (p == len-2) {
                tail[tailLen++] = input[p];
                tail[tailLen++] = input[p+1];
            }
        }

        this.op = op;
        this.count = count;

        return true;
    }
}
