package com.gugu.demo.util.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Administrator
 * @Classname TestFuture
 * @Description TODO
 * @Date 2022/4/17 21:56
 */
public class TestFuture {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        TaskThread taskThread = new TaskThread();
        System.out.println("提交任务...begin");
        Future<String> taskFuture = service.submit(taskThread);
        System.out.println("提交任务...end");
        try {
            Object re = taskFuture.get(60000, TimeUnit.MILLISECONDS);// 超时设置
            System.out.println(re);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("超时 取消任务");
            taskFuture.cancel(true);
            System.out.println("超时 取消任务OK");
        } finally {
            service.shutdown();
        }


    }

    static class TaskThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            String result = "空结果";
            try {
                System.out.println("任务开始....");
                Thread.sleep(600000);
                result = "正确结果";
                System.out.println("任务结束....");
            } catch (Exception e){
                System.out.println("Task is interrupted!");
            }
            return result;
        }
    }
}
