package com.gugu.demo.util.proxy.cglibproxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    /**
     * @Description 获取代理类
     * @params
     * @param c
     * @return java.lang.Object
     * @auther gugu
     */

    public Object getProxy(Class<?> c) {
        // 设置创建子类的类
        enhancer.setSuperclass(c);
        // 回调方法
        enhancer.setCallback(this);
        // 返回代理对象
        return enhancer.create();
    }

    /**
     * 拦截所有目标方法的调用
     * obj 目标类的实例
     * m 目标方法的反射对象
     * args 方法的参数
     * proxy 代理类的实例
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("火车启动啦！！");
        // 代理类调用父类的方法
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("火车停止啦！！");
        return result;
    }
}
