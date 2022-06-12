package algorithm.impl;

import algorithm.RSA;
import algorithm.utils.RSAUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSAImpl implements RSA {

    private final static BigInteger ONE = BigInteger.ONE;
    private BigInteger privateKey;
    private BigInteger e; //частина відкритого ключа - взаємно просте з фі
    private BigInteger modulus; //частина відкритого ключа отримана з n = p*q
    private BigInteger p; //просте число
    private BigInteger q; //просте число
    private final BigInteger phi;// визначене за формулою phi = (p-1)*(q-1)

    public RSAImpl(BigInteger p, BigInteger q, BigInteger e) {
        phi = (p.subtract(ONE)).multiply(q.subtract(ONE)); //phi = (p-1)*(q-1)
        this.e = e;
        this.p = p;
        this.q = q;
        modulus = p.multiply(q);
        privateKey = e.modInverse(phi);//d = e^-1 mod phi, закритий ключ, мультиплікативно обернене до числа е по модулю фі
    }

    /** Шифрування */

    @Override
    public BigInteger encrypt(BigInteger bigInteger) {
        if (isModulusSmallerThanMessage(bigInteger)) throw new IllegalArgumentException("Неможливо зашифрувати - кількість байт повідомлення більше за модуль");
        return bigInteger.modPow(e, modulus);
    }

    public List<BigInteger> encryptMessage(final String message) {
        List<BigInteger> toEncrypt = new ArrayList<BigInteger>();
        BigInteger messageBytes = new BigInteger(message.getBytes());
        if (isModulusSmallerThanMessage(messageBytes)) {
            toEncrypt = getValidEncryptionBlocks(RSAUtils.splitMessages(new ArrayList<String>() {
                {
                    add(message);
                }
            }));
        } else {
            toEncrypt.add((messageBytes));
        }

        List<BigInteger> encrypted = new ArrayList<BigInteger>();
        for (BigInteger bigInteger : toEncrypt) {
            encrypted.add(encrypt(bigInteger));
        }
        return encrypted;
    }

    /** Розшифровка */

    @Override
    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    @Override
    public List<BigInteger> decryptMessages(List<BigInteger> encryption) {
        List<BigInteger> decryption = new ArrayList<BigInteger>();
        for (BigInteger bigInteger : encryption) {
            decryption.add(decrypt(bigInteger));
        }
        return decryption;
    }

    /** Цифровий підпис */

    @Override
    public BigInteger sign(BigInteger bigInteger) {
        return bigInteger.modPow(privateKey, modulus);
    }

    public List<BigInteger> signMessage(final String message) {
        List<BigInteger> toSign = new ArrayList<BigInteger>();
        BigInteger messageBytes = new BigInteger(message.getBytes());
        if (isModulusSmallerThanMessage(messageBytes)) {
            toSign = getValidEncryptionBlocks(RSAUtils.splitMessages(new ArrayList<String>() {
                {
                    add(message);
                }
            }));
        } else {
            toSign.add((messageBytes));
        }
        List<BigInteger> signed = new ArrayList<BigInteger>();
        for (BigInteger bigInteger : toSign) {
            signed.add(sign(bigInteger));
        }
        return signed;
    }

    /** Верифікація */

    @Override
    public BigInteger verifySignedMessage(BigInteger signedMessage) {
        return signedMessage.modPow(e, modulus);
    }

    public List<BigInteger> verify(List<BigInteger> signedMessages) {
        List<BigInteger> verification = new ArrayList<BigInteger>();
        for (BigInteger bigInteger : signedMessages) {
            verification.add(verifySignedMessage(bigInteger));
        }
        return verification;
    }

    @Override
    public boolean isVerified(BigInteger signedMessage, BigInteger message) {
        return verifySignedMessage(signedMessage).equals(message);
    }

    /*
     Забезпечує щоб блоки для шифрування були меньші за модуль
     Повертає список дійсних блоків
    */
    private List<BigInteger> getValidEncryptionBlocks(List<String> messages) {
        List<BigInteger> validBlocks = new ArrayList<BigInteger>();
        BigInteger messageBytes = new BigInteger(messages.get(0).getBytes());
        if (!isModulusSmallerThanMessage(messageBytes)) {
            for (String msg : messages) {
                validBlocks.add(new BigInteger(msg.getBytes()));
            }
            return validBlocks;
        } else //повідомлення більше за модуль, тому потрібно його розбити на менші частини
            return getValidEncryptionBlocks(RSAUtils.splitMessages(messages));
    }

    public List<BigInteger> messageToDecimal(final String message) {
        List<BigInteger> toDecimal = new ArrayList<BigInteger>();
        BigInteger messageBytes = new BigInteger(message.getBytes());
        if (isModulusSmallerThanMessage(messageBytes)) {
            toDecimal = getValidEncryptionBlocks(RSAUtils.splitMessages(new ArrayList<String>() { {
                    add(message);
            }}));
        }
        else toDecimal.add((messageBytes));

        return new ArrayList<BigInteger>(toDecimal);
    }

    private boolean isModulusSmallerThanMessage(BigInteger messageBytes) {
        return modulus.compareTo(messageBytes) < 0;
    }

    @Override
    public String toString() {
        String s = "";
        s += "p                     = " + p + "\n";
        s += "q                     = " + q + "\n";
        s += "e                     = " + e + "\n";
        s += "privateKey            = " + privateKey + "\n";
        s += "modulus               = " + modulus;
        return s;
    }
}
