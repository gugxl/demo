package com.gugu.demo.util.redis;


import java.util.function.Consumer;

/**
 * @author Administrator
 * @Classname Task
 * @Description TODO
 * @Date 2021/11/21 14:51
 */
public class Task implements Runnable {
    // 是否需要考虑多进程互斥（true表示不互斥，多进程能同时跑）
    private boolean concurrent;

    private String name;

    private Runnable runner;

    private transient Consumer<TaskContext> callback = cxt -> {};

    public Task(String name, boolean concurrent, Runnable runner) {
        this.concurrent = concurrent;
        this.name = name;
        this.runner = runner;
    }

    public static Task of(String name, Runnable runner){
        return new Task(name, false, runner);
    }

    public static Task concurrent(String name, Runnable runner){
        return new Task(name, true, runner);
    }


    public boolean isConcurrent() {
        return concurrent;
    }

    public String getName() {
        return name;
    }

    protected void callback(Consumer<TaskContext> callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        boolean ok = false;
        Throwable ex = null;
        long startTs = System.currentTimeMillis();
        try {
            runner.run();
            ok = true;
        } catch (Exception e){
            e.printStackTrace();
            ex = e;
        }
        long cost = System.currentTimeMillis() - startTs;
        TaskContext ctx = new TaskContext(this, cost, ok, ex);
        callback.accept(ctx);
    }
}
