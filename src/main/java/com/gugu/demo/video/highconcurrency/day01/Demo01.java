package com.gugu.demo.video.highconcurrency.day01;

import lombok.SneakyThrows;

/**
 * 死锁
 * https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648933019&idx=1&sn=3455877c451de9c61f8391ffdc1eb01d&chksm=88621aa5bf1593b377e2f090bf37c87ba60081fb782b2371b5f875e4a6cadc3f92ff6d747e32&token=2041017112&lang=zh_CN&scene=21#wechat_redirect
 */
public class Demo01 {
    public static void main(String[] args) {
        Obj1 obj1 = new Obj1();
        Obj2 obj2 = new Obj2();
        new Thread(new SynAddRunalbe(obj1, obj2, 1, 2, true)).start();
        new Thread(new SynAddRunalbe(obj1, obj2, 1, 2, false)).start();
    }

    static class SynAddRunalbe implements Runnable {
        Obj1 obj1;
        Obj2 obj2;
        int a, b;
        boolean flag;

        public SynAddRunalbe(Obj1 obj1, Obj2 obj2, int a, int b, boolean flag) {
            this.obj1 = obj1;
            this.obj2 = obj2;
            this.a = a;
            this.b = b;
            this.flag = flag;
        }

        @SneakyThrows
        @Override
        public void run() {
            if (flag) {
                synchronized (obj1) {
                    Thread.sleep(1000L);
                    synchronized (obj2) {
                        System.out.println(a + b);
                    }
                }
            } else {
                synchronized (obj2) {
                    Thread.sleep(1000L);
                    synchronized (obj1) {
                        System.out.println(a + b);
                    }
                }
            }

        }
    }

    static class Obj1 {
    }

    static class Obj2 {
    }
}
