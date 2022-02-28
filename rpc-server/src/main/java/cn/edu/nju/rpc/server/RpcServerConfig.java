package cn.edu.nju.rpc.server;

import cn.edu.nju.rpc.codec.Decoder;
import cn.edu.nju.rpc.codec.Encoder;
import cn.edu.nju.rpc.codec.JSONDecoder;
import cn.edu.nju.rpc.codec.JSONEncoder;
import cn.edu.nju.rpc.transport.TransportServer;
import cn.edu.nju.rpc.transport.impl.HttpTransportServer;
import lombok.Data;


@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass= HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private int port = 3000;

}
