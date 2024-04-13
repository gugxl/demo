package com.gugu.demo.util.thread;

/**
 * @author Administrator
 * @Classname ThreadTest2
 * @Description TODO
 * @Date 2021/10/16 23:46
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        new ThreadTest2().init();
    }

    private void init() {
        Business business =new Business();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    business.SubThread(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    business.MainThread(i);
                }
            }
        }).start();

    }

    class Business {
        boolean flag = true;//定义控制该谁执行的一个信号灯

        public synchronized void MainThread(int i){
            if (flag){
                try {
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 100; j++) {
                System.out.println(Thread.currentThread().getName()+ ":i=" + i +",j=" + j);
            }

            flag = true;
            this.notify();
        }

        public synchronized void SubThread(int i){
            if (!flag){
                try {
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 10; j++) {
                System.out.println(Thread.currentThread().getName()+ ":i=" + i +",j=" + j);
            }

            flag = false;
            this.notify();
        }
    }
}
