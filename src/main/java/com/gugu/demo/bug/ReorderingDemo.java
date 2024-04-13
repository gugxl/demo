package com.gugu.demo.bug;

/**
 * @author Administrator
 * @Classname ReorderingDemo
 * @Description TODO
 * @Date 2022/3/6 20:11
 */
public class ReorderingDemo {
    static int x = 0, y = 0 , a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(){
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        };
        Thread two = new Thread(){
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        };
        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(x + " " + y);
    }


}
