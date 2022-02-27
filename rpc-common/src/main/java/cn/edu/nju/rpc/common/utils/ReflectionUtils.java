package cn.edu.nju.rpc.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    public static <T> T newInstance(Class<T>clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    /**
     * get public methods
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method>pmethods = new ArrayList<>();
        for(Method m:methods){
            if(Modifier.isPublic(m.getModifiers())){
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    /**
     * call object's method
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object obj,Method method,Object... args){
        try{
            return method.invoke(obj,args);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
