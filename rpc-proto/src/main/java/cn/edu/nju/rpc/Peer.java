package cn.edu.nju.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 一个端点
 */
@Data
@AllArgsConstructor
public class Peer {
    private int host;
    private int port;
}
