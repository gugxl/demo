package com.gugu.demo.util.redis;

/**
 * @author Administrator
 * @Classname Holder
 * @Description TODO
 * @Date 2021/11/21 12:57
 */
public class Holder<T> {
    private T value;

    public Holder() {
    }

    public Holder(T value) {
        this.value = value;
    }

    public void value(T value){
        this.value = value;
    }

    public T value(){
        return value;
    }
}
