package com.gugu.demo.book.book1.chapter3;

public class Demo13 {

    public static void main(String[] args){




    }


    static class ThreadBackupA extends Thread{
        private DBTools dbTools;

        public ThreadBackupA(DBTools dbTools) {
            this.dbTools = dbTools;
        }

        @Override
        public void run() {
            dbTools.backupA();
        }
    }
    static class ThreadBackupB extends Thread{
        private DBTools dbTools;

        public ThreadBackupB(DBTools dbTools) {
            this.dbTools = dbTools;
        }

        @Override
        public void run() {
            dbTools.backupB();
        }
    }

    static class DBTools {
        volatile private boolean prevIsA = false;

        synchronized void backupA() {
            try {
                while (prevIsA)
                    wait();

                for (int i = 0; i < 5; i++) {
                    System.out.println("*");
                }

                prevIsA = true;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        synchronized void backupB(){
            try {
                while (!prevIsA)
                    wait();

                for (int i = 0; i < 5; i++) {
                    System.out.println("+");
                }

                prevIsA = false;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
