package com.gugu.demo.other;

/**
 * @author Administrator
 * @Classname Person
 * @Description TODO
 * @Date 2021/4/1 23:04
 */
public interface Person {
    //小王和小李通过这个接口可以接收到小美发过来的消息
    void getMessage(String s);
}
