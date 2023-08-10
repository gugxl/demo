package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo05 {
    public static void main(String[] args) {
        ALogin aLogin = new ALogin();
        aLogin.start();
        BLogin bLogin = new BLogin();
        bLogin.start();
    }
}

class LoginServlet {
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

class ALogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("a", "aa");
    }
}

class BLogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("b", "bb");
    }
}
