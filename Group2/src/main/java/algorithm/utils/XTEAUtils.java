package algorithm.utils;

public class XTEAUtils {
    public int[] byteToInt(byte[] content, int offset){
        int[] result = new int[content.length >> 2];
        for(int i = 0, j = offset; j < content.length; i++, j += 4){
            result[i] = transform(content[j + 3]) | transform(content[j + 2]) << 8 |
                    transform(content[j + 1]) << 16 | (int)content[j] << 24;
        }
        return result;
    }

    public byte[] intToByte(int[] content){
        byte[] result = new byte[content.length << 2];
        for(int i = 0, j = 0; j < result.length; i++, j += 4){
            result[j + 3] = (byte)(content[i] & 0xff);
            result[j + 2] = (byte)((content[i] >> 8) & 0xff);
            result[j + 1] = (byte)((content[i] >> 16) & 0xff);
            result[j] = (byte)((content[i] >> 24) & 0xff);
        }
        return result;
    }

    private int transform(byte temp){
        int tempInt = (int)temp;
        if(tempInt < 0){
            tempInt += 256;
        }
        return tempInt;
    }

    public int toInt(char a) {
        if (a >= '0' && a <= '9')
            return a - '0';
        if (a >= 'A' && a <= 'F')
            return a - 55;
        if (a >= 'a' && a <= 'f')
            return a - 87;
        return 0;
    }

    public String replacePlus(String paramTea){
        String teaStr = "";
        if(paramTea!=null && !"".equals(paramTea)){
            teaStr = paramTea.replace("+", "%2B");
        }
        return teaStr;
    }

    public String addPlus(String paramTea){
        String teaStr = "";
        if(paramTea!=null && !"".equals(paramTea)){
            teaStr = paramTea.replace("%2B", "+");
        }
        return teaStr;
    }
}
