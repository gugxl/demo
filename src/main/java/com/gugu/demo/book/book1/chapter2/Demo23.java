package com.gugu.demo.book.book1.chapter2;

public class Demo23 {

    public static void main(String[] args) {
        PrintString printString = new PrintString();
        new Thread(printString).start();
        System.out.println(" stop print " + Thread.currentThread().getName());
        printString.setContinute(false);
    }

    static class PrintString implements Runnable {

        public boolean isContinute() {
            return isContinute;
        }

        public void setContinute(boolean continute) {
            isContinute = continute;
        }

        private boolean isContinute = true;

        private void printString() {
            while (isContinute == true) {
                System.out.println("run printString method " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public void run() {
            printString();
        }
    }
}
