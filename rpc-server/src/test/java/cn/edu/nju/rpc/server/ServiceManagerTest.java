package cn.edu.nju.rpc.server;

import cn.edu.nju.rpc.Request;
import cn.edu.nju.rpc.ServiceDescriptor;
import cn.edu.nju.rpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    ServiceManager sm;
    @Before
    public void init(){
        sm = new ServiceManager();
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);
    }

    @Test
    public void register() {

    }

    @Test
    public void lookup() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestInterface.class);
        ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(TestInterface.class, methods[0]);
        Request request = new Request();
        request.setServiceDescriptor(serviceDescriptor);

        ServiceInstance instance = sm.lookup(request);
        assertNotNull(instance);

    }
}