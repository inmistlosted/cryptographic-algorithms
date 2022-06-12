package algorithms;

import java.math.BigInteger;

public class KaratsubaMultiplication {
    public static final int MIN_BIT_NUM = 2;

    public static BigInteger multiply(BigInteger x, BigInteger y) {
        int bitNum = Math.max(x.bitLength(), y.bitLength());
        if (bitNum <= MIN_BIT_NUM) return x.multiply(y);

        // число бітів поділене на 2 та округлене
        bitNum = (bitNum / 2) + (bitNum % 2);

        /*
         x = a + b*2^bitNum
         y = c + d*2^bitNum
        */
        BigInteger b = x.shiftRight(bitNum);
        BigInteger a = x.subtract(b.shiftLeft(bitNum));
        BigInteger d = y.shiftRight(bitNum);
        BigInteger c = y.subtract(d.shiftLeft(bitNum));

        // x * y = a * c + ((a+b)*(c+d) — a*c — b*d) * 2^m + b*d*2^(2*bitNum)
        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger abcd = multiply(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(bitNum)).add(bd.shiftLeft(2 * bitNum));
    }
}
