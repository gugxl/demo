package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public class Demo07 {
    public static void main(String[] args) {
        Service07 service07 = new Service07();
        new Thread07("a", service07).start();
        new Thread07("b", service07).start();
    }

    static class Service07 {
        String userName;
        String password;
        String anySTring = new String();

        @SneakyThrows
        void setUserNameAndPassword(String userName, String password){
            synchronized (anySTring){
                System.out.println("threadName="+Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步块");
                this.userName = userName;
                Thread.sleep(1000);
                this.password = password;
                System.out.println("threadName="+Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步块");
            }
        }
    }

    static class Thread07 extends Thread{
        private Service07 service07;

        public Thread07(@NotNull String name, Service07 service07) {
            super(name);
            this.service07 = service07;
        }

        @Override
        public void run() {
            service07.setUserNameAndPassword("b", "bb");
        }
    }

}
