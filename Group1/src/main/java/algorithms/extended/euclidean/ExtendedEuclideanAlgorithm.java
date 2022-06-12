package algorithms.extended.euclidean;

import java.math.BigInteger;

public class ExtendedEuclideanAlgorithm {

    /*
     Розширений алгоритм Евкліда.
     Рахує НСД чисел a i b, а також коефіцієнти х та у, що задовільняють рівняння Безу
     a*x + b*y = НСД(a, b), де НСД - це найбільший спільний дільник a і b.
    */
    public static BezoutIdentity calculateGcd(BigInteger a, BigInteger b){
        BezoutIdentity identity = new BezoutIdentity(a, b);

        BigInteger prevX = BigInteger.ONE;
        BigInteger x = BigInteger.ZERO;
        BigInteger prevY = BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;

        while (b.compareTo(BigInteger.ZERO) != 0){
            BigInteger quotient = a.divide(b),
                        xCopy = new BigInteger(x.toByteArray()),
                        yCopy = new BigInteger(y.toByteArray());

            x = prevX.subtract(quotient.multiply(x));
            prevX = xCopy;

            y = prevY.subtract(quotient.multiply(y));
            prevY = yCopy;

            BigInteger prevA = new BigInteger(a.toByteArray());
            a = b;
            b = prevA.mod(b);
        }

        identity.setGcd(a);
        identity.setX(prevX);
        identity.setY(prevY);

        return identity;
    }
}
