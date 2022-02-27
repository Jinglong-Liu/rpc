package cn.edu.nju.rpc.server;

import cn.edu.nju.rpc.Request;
import cn.edu.nju.rpc.ServiceDescriptor;
import cn.edu.nju.rpc.common.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * manager service
 */
public class ServiceManager {
    private Map<ServiceDescriptor,ServiceInstance>services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }
    public void register(Class interfaceClass,Object bean){
        final Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for(Method method:methods){
            ServiceInstance sis = new ServiceInstance(bean,method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass,method);

            services.put(sdp,sis);
        }
    }
    public ServiceInstance lookup(Request request){
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
