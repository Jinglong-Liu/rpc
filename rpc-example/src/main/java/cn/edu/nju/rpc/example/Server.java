package cn.edu.nju.rpc.example;

import cn.edu.nju.rpc.server.RpcServer;
import cn.edu.nju.rpc.server.RpcServerConfig;


public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class,new CalcServiceImpl());
        server.start();
    }
}
