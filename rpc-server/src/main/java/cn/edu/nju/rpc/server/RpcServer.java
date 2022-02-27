package cn.edu.nju.rpc.server;

import cn.edu.nju.rpc.Request;
import cn.edu.nju.rpc.Response;
import cn.edu.nju.rpc.ServiceDescriptor;
import cn.edu.nju.rpc.codec.Decoder;
import cn.edu.nju.rpc.codec.Encoder;
import cn.edu.nju.rpc.common.utils.ReflectionUtils;
import cn.edu.nju.rpc.transport.RequestHandler;
import cn.edu.nju.rpc.transport.TransportServer;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

@Slf4j
public class RpcServer {

    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;
    public RpcServer(RpcServerConfig config){
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        // service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }
    public void register(Class interfaceClass,Object bean){
        serviceManager.register(interfaceClass,bean);
    }
    public void start(){
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResponse) {
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive,receive.available());
                Request request = decoder.decode(inBytes,Request.class);
                log.info("get request: {}",request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis,request);

            } catch (Exception e) {
                e.printStackTrace();
                log.warn(e.getMessage(),e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: " + e.getClass().getName()+" "+e.getMessage());
            } finally {
                byte[] outBytes = encoder.encode(resp);
                try {
                    toResponse.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }

        }
    };
}
