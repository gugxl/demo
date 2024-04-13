package com.gugu.demo.util.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Administrator
 * @Classname FutureTaskDemo
 * @Description TODO
 * @Date 2021/5/3 11:41
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        ACallAble aCallAble = new ACallAble();
        FutureTask<String> futureTask = new FutureTask<>(aCallAble);
        Thread thread = new Thread(futureTask);
        thread.start();
        int c = 0;
        do {
        } while (!futureTask.isDone());

        String result = null;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Result:" + result);

    }
}
class ACallAble implements Callable<String>{
    @Override
    public String call() throws Exception {
        Thread.sleep(10000);
        return "Thread-Name:" + Thread.currentThread().getName();
    }
}
