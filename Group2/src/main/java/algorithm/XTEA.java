package algorithm;

import algorithm.utils.Base64;
import algorithm.utils.XTEAUtils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

/** Лабораторна робота №2 - алгоритм XTEA */

public class XTEA {

    private final int[] KEY = new int[]{
            0x789f5645, 0xf68bd5a4,
            0x81963ffa, 0xabcdef12
    };

    private final int TIMES = 32;
    private XTEAUtils utils;

    public XTEA(){
        utils = new XTEAUtils();
    }

    private byte[] encrypt(byte[] content, int offset, int times){
        int[] tempInt = utils.byteToInt(content, offset);
        int y = tempInt[0], z = tempInt[1], sum = 0;
        int delta=0x9e3779b9;
        while (times>0){
            sum += delta;
            y += ((z<<4) + KEY[0]) ^ (z + sum) ^ ((z>>5) + KEY[1]);
            z += ((y<<4) + KEY[2]) ^ (y + sum) ^ ((y>>5) + KEY[3]);
            times -= 1;
        }
        tempInt[0]=y;
        tempInt[1]=z;
        return utils.intToByte(tempInt);
    }

    private byte[] decrypt(byte[] encryptContent, int offset, int times){
        int[] tempInt = utils.byteToInt(encryptContent, offset);
        int y = tempInt[0], z = tempInt[1], sum ;
        int delta=0x9e3779b9;
        if (times == 32)
            sum = 0xC6EF3720;
        else if (times == 16)
            sum = 0xE3779B90;
        else
            sum = delta * times;

        while (times>0){
            z -= ((y<<4) + KEY[2]) ^ (y + sum) ^ ((y>>5) + KEY[3]);
            y -= ((z<<4) + KEY[0]) ^ (z + sum) ^ ((z>>5) + KEY[1]);
            sum -= delta;
            times -= 1;
        }
        tempInt[0] = y;
        tempInt[1] = z;

        return utils.intToByte(tempInt);
    }



    private byte[] encryptByXTEA(String info){
        byte[] temp = info.getBytes();
        int n = 8 - temp.length % 8;
        byte[] encryptStr = new byte[temp.length + n];
        encryptStr[0] = (byte)n;
        System.arraycopy(temp, 0, encryptStr, n, temp.length);
        byte[] result = new byte[encryptStr.length];
        for(int offset = 0; offset < result.length; offset += 8){
            byte[] tempEncrypt = encrypt(encryptStr, offset, TIMES);
            System.arraycopy(tempEncrypt, 0, result, offset, 8);
        }
        return result;
    }

    public String encryptByBase64XTEA(String info) throws UnsupportedEncodingException {
        byte[] compressedBytes = info.getBytes("UTF8");
        String hexStr = bytes2hex(compressedBytes);
        byte[] teaBytes = encryptByXTEA(hexStr);
        String base64 = Base64.encodeToString(teaBytes);
        return utils.replacePlus(base64);
    }

    public String decryptByXTEA(byte[] secretInfo){
        byte[] decryptStr = null;
        byte[] tempDecrypt = new byte[secretInfo.length];
        for(int offset = 0; offset < secretInfo.length; offset += 8){
            decryptStr = decrypt(secretInfo, offset, TIMES);
            System.arraycopy(decryptStr, 0, tempDecrypt, offset, 8);
        }
        int n = tempDecrypt[0];
        return new String(tempDecrypt, n, decryptStr.length - n);
    }

    public String decryptByBase64XTEA(String secretInfo){
        byte[] hexBytes = null;
        try {
            String info = utils.addPlus(secretInfo);
            byte[] decodeStr = Base64.decodeString(info);
            String teaStr = decryptByXTEA(decodeStr);
            hexBytes = hex2bytes(teaStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = null;
        if(hexBytes!=null && hexBytes.length>0) {
            try {
                str = new String(hexBytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    private String bytes2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String temp = Integer.toHexString(aByte);
            if (temp.length() == 1) {
                temp = "0" + temp;
            } else {
                temp = temp.substring(temp.length() - 2);
            }
            sb.append(temp);
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    private byte[] hex2bytes(String hex) {
        if (hex.length() % 2 != 0)
            hex = "0" + hex;
        int len = hex.length() / 2;
        byte[] val = new byte[len];
        for (int i = 0; i < len; i++) {
            val[i] = (byte) (utils.toInt(hex.charAt(2 * i)) * 16 + utils.toInt(hex.charAt(2 * i + 1)));
        }
        return val;
    }
}
