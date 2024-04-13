package com.gugu.demo.util.redis;

import java.util.function.Supplier;

/**
 * @author Administrator
 * @Classname ThreadLocalObject
 * @Description TODO
 * @Date 2021/11/21 15:42
 */
public class ThreadLocalObject<T> {

    private Supplier<T> supplier;

    public ThreadLocalObject(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    private ThreadLocal<T> holder = new ThreadLocal<T>();

    public T take() {
        T o = holder.get();
        if (o == null) {
            o = supplier.get();
            holder.set(o);
        }
        return o;
    }
}
