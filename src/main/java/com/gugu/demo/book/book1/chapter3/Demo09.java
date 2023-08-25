package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;

public class Demo09 {
    public static void main(String[] args) {
        String lock = new String();
        P p = new P(lock);
        C c = new C(lock);
        new Thread(() -> {
            while (true)
                p.setValue();
        }, "ThreadP").start();
        new Thread(() -> {
            while (true)
                c.getValue();
        }, "ThreadC").start();


    }

    // 生产者
    static class P {
        private String lock;

        public P(String lock) {
            this.lock = lock;
        }

        @SneakyThrows
        public void setValue() {
            synchronized (lock) {
                if (!ValueObject.value.equals("")) {
                    lock.wait();
                }

                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("set 的 value " + value);
                ValueObject.value = value;
                lock.notify();
            }
        }
    }

    // 消费者
    static class C {
        private String lock;

        public C(String lock) {
            this.lock = lock;
        }

        @SneakyThrows
        public void getValue() {
            synchronized (lock) {
                if (ValueObject.value.equals("")) {
                    lock.wait();
                }

                System.out.println("get 到的 value " + ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }
        }
    }

    static class ValueObject {
        static String value = "";
    }
}
