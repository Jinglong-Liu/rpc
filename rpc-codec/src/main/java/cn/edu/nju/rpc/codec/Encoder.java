package cn.edu.nju.rpc.codec;

public interface Encoder {
    byte[] encode(Object obj);
}
