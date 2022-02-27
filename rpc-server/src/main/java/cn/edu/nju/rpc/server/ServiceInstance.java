package cn.edu.nju.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
