package cn.edu.nju.rpc.server;

import cn.edu.nju.rpc.Request;
import cn.edu.nju.rpc.common.utils.ReflectionUtils;

/**
 * invoke service
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }
}
