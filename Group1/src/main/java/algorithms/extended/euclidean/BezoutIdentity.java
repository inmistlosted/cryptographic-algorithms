package algorithms.extended.euclidean;

import java.math.BigInteger;

/*
  Клас відтворює рівняння Безу
  a*x + b*y = НСД(a, b), де НСД - це найбільший спільний дільник чисел a та b.
*/
public class BezoutIdentity {
    private BigInteger a;
    private BigInteger b;
    private BigInteger x;
    private BigInteger y;
    private BigInteger gcd;

    public BezoutIdentity(BigInteger a, BigInteger b, BigInteger x, BigInteger y, BigInteger gcd) {
        this.a = a;
        this.b = b;
        this.x = x;
        this.y = y;
        this.gcd = gcd;
    }

    public BezoutIdentity(BigInteger a, BigInteger b) {
        this.a = a;
        this.b = b;
    }

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public BigInteger getGcd() {
        return gcd;
    }

    public void setGcd(BigInteger gcd) {
        this.gcd = gcd;
    }

    public BezoutIdentity(){

    }

    @Override
    public String toString() {
        return "x = " + x + "\n" +
                "y = " + y + "\n" +
                "НСД( " + a + ", " + b + " ) = " + gcd + "\n" +
                "Перевірка: " + (a.multiply(x).add(b.multiply(y))) + " = " + gcd;
    }
}
