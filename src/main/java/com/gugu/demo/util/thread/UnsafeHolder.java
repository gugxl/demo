package com.gugu.demo.util.thread;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Administrator
 * @Classname UnsafeHolder
 * @Description TODO
 * @Date 2021/5/3 23:07
 */
public class UnsafeHolder {
    private static Unsafe U = null;
    public static Unsafe getUnsafe() {
        if (U == null) {
            synchronized (UnsafeHolder.class) {
                if (U == null) {
                    List<Exception> exception = null;
                    try {
                        Field field = Unsafe.class.getDeclaredField("theUnsafe");
                        field.setAccessible(true);
                        try {
                            U = (Unsafe) field.get(null);
                        } catch (IllegalAccessException e) {
                            exception.add(e);
                        }
                    } catch (NoSuchFieldException e) {
                        exception.add(e);
                    } finally {
                        if (exception != null) {
                            reportException(exception);
                        }
                    }
                }
            }
        }
        return U;
    }

    /**
     * handler the exception in this method .
     * @param e The exception
     */
    private static void reportException(List<Exception> e) {
        e.forEach(System.out::println);
    }
}


