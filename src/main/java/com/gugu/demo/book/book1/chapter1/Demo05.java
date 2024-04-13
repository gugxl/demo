package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo05 {
    public static void main(String[] args) {
        new Thread(() -> {
            LoginServlet.doPost("a", "aa");
        }).start();

        new Thread(() -> {
            LoginServlet.doPost("b", "bbs");
        }).start();

    }

    static class LoginServlet {
        private static String userNameRef;
        private static String passwordRef;

        // synchronized
        @SneakyThrows
        public static void doPost(String userName, String password) {
            userNameRef = userName;
            if (userName.equals("a")) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username:" + userNameRef + ",password:" + passwordRef);
        }
    }

}


