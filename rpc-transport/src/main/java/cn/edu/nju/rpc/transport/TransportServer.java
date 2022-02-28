package cn.edu.nju.rpc.transport;

public interface TransportServer {
    void init(int port , RequestHandler handler);

    void start();

    void stop();
}
