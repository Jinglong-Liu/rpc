package cn.edu.nju.rpc.client;

import cn.edu.nju.rpc.Peer;
import cn.edu.nju.rpc.transport.TransportClient;
import sun.plugin2.message.transport.Transport;

import java.util.List;

/**
 * 选择哪个server去连接
 */
public interface TransportSelector {
    /**
     * 初始化
     * @param peers 可以连接的server端点
     * @param count 建立多少个连接
     * @param clazz 实现类
     */
    void init(List<Peer> peers,
              int count,
              Class<? extends TransportClient>clazz);
    TransportClient select();

    /**
     * 释放
     * @param client
     */
    void release(TransportClient client);

    void close();
}
