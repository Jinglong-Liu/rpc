package cn.edu.nju.rpc.example;

import cn.edu.nju.rpc.client.RpcClient;
import cn.edu.nju.rpc.client.RpcClientConfig;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient(new RpcClientConfig());
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(1,2);
        int r2 = service.minus(10,8);

        System.out.println(r1);
        System.out.println(r2);
    }
}
