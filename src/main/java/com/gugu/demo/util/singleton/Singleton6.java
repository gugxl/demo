package com.gugu.demo.util.singleton;

public class Singleton6 {
    private volatile static Singleton6 singleton;

    private Singleton6() {
    }

    public static Singleton6 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton6.class) {
                if (singleton == null) {
                    singleton = new Singleton6();
                }
            }
        }
        return singleton;
    }
}