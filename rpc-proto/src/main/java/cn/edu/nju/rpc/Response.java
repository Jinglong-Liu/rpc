package cn.edu.nju.rpc;

import lombok.Data;

/**
 * 表示RPC的返回
 */
@Data
public class Response {
    /**
     * 服务返回编码，0-成功 非0-失败
     */
    private int code;
    private String message = "ok";
    private Object data;
}
