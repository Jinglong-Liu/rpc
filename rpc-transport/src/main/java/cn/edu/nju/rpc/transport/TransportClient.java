package cn.edu.nju.rpc.transport;

import cn.edu.nju.rpc.Peer;

import java.io.InputStream;

public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
