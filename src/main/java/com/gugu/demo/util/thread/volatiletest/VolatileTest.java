package com.gugu.demo.util.thread.volatiletest;

/**
 * @author Administrator
 * @Classname VolatileTest
 * @Description TODO
 * @Date 2022/6/3 8:07
 */
public class VolatileTest {

    // 如果没有volatile 将会一直循环，直到cpu刷新缓存，将线程内数据刷新到主线，才会结束
    private static volatile boolean isStop = false;

    public static void stop(){
        isStop = true;
    }
    private static int i = 0;
    public static void main(String[] args) {

        new Thread1().start();
        while (!isStop){
            i++;
            // 如果有下面的一行，即使上面的没有volatile也会正常退出，因为print底层使用了synchronized
//            System.out.print("1");
        }
        System.out.println("stop");
    }

    static class Thread1 extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            VolatileTest.stop();
        }
    }
}
