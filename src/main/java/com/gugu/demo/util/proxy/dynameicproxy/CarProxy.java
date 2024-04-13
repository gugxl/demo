package com.gugu.demo.util.proxy.dynameicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gugu
 * @Classname CarProxy
 * @Description 动态代理类
 * @Date 2022/9/3 18:07
 */
public class CarProxy implements InvocationHandler {
    private Object target;

    //这个方法并不是我们自己去调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("该客户是否是vip客户");
        // 执行委托类的方法
        Object result = method.invoke(target,args);
        System.out.println("该客户买车完成");
        return result;

    }

    public Object bind(Object target) {
        this.target = target;
        // 反射获取对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                this);
    }

}
