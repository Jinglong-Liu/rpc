package cn.edu.nju.rpc;

import lombok.Data;

/**
 * RPC请求
 */
@Data
public class Request {
    private ServiceDescriptor serviceDescriptor;
    private Object[] parameters;
}
