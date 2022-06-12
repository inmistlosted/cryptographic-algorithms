package algorithm.utils;

public class Decoder extends Coder {
    /*
     Таблиця пошуку для перетворення байтів у їх положення в алфавіті Base64.
    */
    private static final int[] DECODE = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1,
            -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    };

    private static final int SKIP = -1;
    private static final int EQUALS = -2;
    private int state;
    private int value;
    final private int[] alphabet;

    public Decoder(byte[] output) {
        this.output = output;
        alphabet = DECODE;
        state = 0;
        value = 0;
    }


    public int maxOutputSize(int len) {
        return len * 3/4 + 10;
    }

    public boolean process(byte[] input, int offset, int len, boolean finish) {
        if (this.state == 6) return false;

        int p = offset;
        len += offset;

        int state = this.state;
        int value = this.value;
        int op = 0;
        final byte[] output = this.output;
        final int[] alphabet = this.alphabet;

        while (p < len) {
            if (state == 0) {
                while (p+4 <= len &&
                        (value = ((alphabet[input[p] & 0xff] << 18) |
                                (alphabet[input[p+1] & 0xff] << 12) |
                                (alphabet[input[p+2] & 0xff] << 6) |
                                (alphabet[input[p+3] & 0xff]))) >= 0) {
                    output[op+2] = (byte) value;
                    output[op+1] = (byte) (value >> 8);
                    output[op] = (byte) (value >> 16);
                    op += 3;
                    p += 4;
                }
                if (p >= len) break;
            }

            int d = alphabet[input[p++] & 0xff];

            switch (state) {
                case 0:
                    if (d >= 0) {
                        value = d;
                        ++state;
                    } else if (d != SKIP) {
                        this.state = 6;
                        return false;
                    }
                    break;

                case 1:
                    if (d >= 0) {
                        value = (value << 6) | d;
                        ++state;
                    } else if (d != SKIP) {
                        this.state = 6;
                        return false;
                    }
                    break;

                case 2:
                    if (d >= 0) {
                        value = (value << 6) | d;
                        ++state;
                    } else if (d == EQUALS) {
                        output[op++] = (byte) (value >> 4);
                        state = 4;
                    } else if (d != SKIP) {
                        this.state = 6;
                        return false;
                    }
                    break;

                case 3:
                    if (d >= 0) {
                        value = (value << 6) | d;
                        output[op+2] = (byte) value;
                        output[op+1] = (byte) (value >> 8);
                        output[op] = (byte) (value >> 16);
                        op += 3;
                        state = 0;
                    } else if (d == EQUALS) {
                        output[op+1] = (byte) (value >> 2);
                        output[op] = (byte) (value >> 10);
                        op += 2;
                        state = 5;
                    } else if (d != SKIP) {
                        this.state = 6;
                        return false;
                    }
                    break;

                case 4:
                    if (d == EQUALS) {
                        ++state;
                    } else if (d != SKIP) {
                        this.state = 6;
                        return false;
                    }
                    break;

                case 5:
                    if (d != SKIP) {
                        this.state = 6;
                        return false;
                    }
                    break;
            }
        }

        if (!finish) {
            this.state = state;
            this.value = value;
            this.op = op;
            return true;
        }

        switch (state) {
            case 0:
            case 5:
                break;
            case 1:
            case 4:
                this.state = 6;
                return false;
            case 2:
                output[op++] = (byte) (value >> 4);
                break;
            case 3:
                output[op++] = (byte) (value >> 10);
                output[op++] = (byte) (value >> 2);
                break;
        }

        this.state = state;
        this.op = op;
        return true;
    }
}
