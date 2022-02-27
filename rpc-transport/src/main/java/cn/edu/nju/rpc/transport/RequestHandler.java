package cn.edu.nju.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网路请求的handler
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream toRecv);
}
