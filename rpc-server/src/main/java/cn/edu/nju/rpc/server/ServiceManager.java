package cn.edu.nju.rpc.server;

import cn.edu.nju.rpc.Request;
import cn.edu.nju.rpc.ServiceDescriptor;
import cn.edu.nju.rpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * manager service
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance>services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }
    public <T> void register(Class<T> interfaceClass,T bean){
        final Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for(Method method:methods){
            ServiceInstance sis = new ServiceInstance(bean,method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass,method);

            services.put(sdp,sis);
            log.info("register service {} {}",sdp.getClazz(),sdp.getMethod());
        }
    }
    public ServiceInstance lookup(Request request){
        ServiceDescriptor sdp = request.getServiceDescriptor();
        return services.get(sdp);
    }
}
