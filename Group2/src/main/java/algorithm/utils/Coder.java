package algorithm.utils;

public abstract class Coder {
    public byte[] output;
    public int op;

    public abstract boolean process(byte[] input, int offset, int len, boolean finish);
    public abstract int maxOutputSize(int len);
}
