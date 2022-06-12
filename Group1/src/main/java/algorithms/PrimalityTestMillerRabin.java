package algorithms;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

public class PrimalityTestMillerRabin {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");
    private static final int NUM_OF_ITERS = 7000;

    /*
     перевіряє число number на простоту
    */
    public static boolean isPrime(BigInteger number, int k) throws Exception {
        if (number.compareTo(ONE) <= 0) return false;
        if (number.compareTo(TWO) == 0) return true;

        BigInteger d = new BigInteger(String.valueOf(number)).subtract(ONE);
        int s = 0;
        while (d.mod(TWO).compareTo(ZERO) == 0){
            s++;
            d = d.divide(TWO);
        }

        mainLoop:
        for (int i = 0; i < k; i++){
            BigInteger randBase = randomValue(number);
            BigInteger x = randBase.modPow(d, number);
            if ((x.compareTo(ONE) == 0) || (x.compareTo(number.subtract(ONE)) == 0)) continue;

            for (int j = 0; j < (s - 1); j++){
                x = x.modPow(TWO, number);
                if (x.compareTo(ONE) == 0) return false;
                if (x.compareTo(number.subtract(ONE)) == 0) continue mainLoop;
            }
            return false;
        }

        return true;
    }

    /*
     повертає випадкове число з проміжку [2, n-2]
    */
    private static BigInteger randomValue(BigInteger number) throws Exception {
        Random rand = new Random(new Date().getTime());
        for (int i = 0; i < NUM_OF_ITERS; i++) {
            BigInteger res = new BigInteger(number.bitLength(), rand);
            if ((res.compareTo(TWO) >= 0) && (res.compareTo(number.subtract(TWO)) <= 0)) return res;
        }
        throw new Exception("Iterations out");
    }
}
