package com.gugu.demo.util.redis;

/**
 * @author Administrator
 * @Classname ISchedulerListener
 * @Description TODO
 * @Date 2021/11/21 19:06
 */
public interface ISchedulerListener {

    public void onComplete(TaskContext ctx);

    public default void onStartup() {}

    public default void onReload() {}

    public default void onStop() {}
}
