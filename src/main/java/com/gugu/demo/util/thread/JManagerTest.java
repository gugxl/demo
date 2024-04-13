package com.gugu.demo.thread;

/**
 * @author Administrator
 * @Classname JManagerTest
 * @Description TODO
 * @Date 2021/10/16 23:40
 */
public class JManagerTest {
    JManager jManager = new JManager();
    public static void main(String[] args) {
        new JManagerTest().call();
    }

    private void call() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        jManager.accumulate();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        jManager.subtract();
                    }
                }
            }).start();
        }
    }


    class JManager{
        private int i = 0;
        public synchronized void subtract(){
            System.out.println(i--);
        }
        public synchronized void accumulate(){
            System.out.println(i++);
        }

    }
}
