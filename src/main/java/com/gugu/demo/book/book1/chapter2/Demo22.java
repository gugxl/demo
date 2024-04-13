package com.gugu.demo.book.book1.chapter2;

import lombok.Data;
import lombok.SneakyThrows;

// 死循环，因为main线程一直在处理while循环
public class Demo22 {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("stop ThreadName" + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }

    @Data
    static class PrintString {
        private boolean isContinuePrint = true;

        @SneakyThrows
        public void printStringMethod() {
            while (isContinuePrint == true) {
                System.out.println("run printStringMethod threadName " + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        }
    }
}
