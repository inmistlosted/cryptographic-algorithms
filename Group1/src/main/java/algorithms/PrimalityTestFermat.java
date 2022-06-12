package algorithms;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

public class PrimalityTestFermat {
    public static int numOfIterations = 500;

    /*
     Цей метод перевіряє умову для випадкової основи
     а саме перевіряє умову: (base ^ (number - 1) mod (number) == 1)
     numOfIterations кількість разів
     Якщо існує хоч один випадок, що дає false, повертається результат,
     що число number не просте, в іншому випадку число number просте
    */
    public static boolean isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.ONE) <= 0){
            return false;
        } else if (number.compareTo(BigInteger.valueOf(2)) == 0){
            return true;
        }

        int i = 0;
        while (i != numOfIterations){
            if (getRandFermatBase(number).modPow(number.subtract(BigInteger.ONE), number)
                    .compareTo(BigInteger.ONE) != 0) return false;
            i++;
        }
        return true;
    }

    /*
     повертає число, що називається base, яке задовільняє умовам:
     base > 1 - число number невід'ємне, немає сенсу робити перевірку для бази 1
     і base < p - щоб упевнитися, що число number не може бути поділене на base (перевірка умови Ферма)
    */
    private static BigInteger getRandFermatBase(BigInteger number) {
        Random rand = new Random(new Date().getTime());
        while(true){
            BigInteger base = new BigInteger(number.bitLength(), rand);
            if ((base.compareTo(number) < 0) && (base.compareTo(BigInteger.ONE) > 0)) return base;
        }
    }
}
