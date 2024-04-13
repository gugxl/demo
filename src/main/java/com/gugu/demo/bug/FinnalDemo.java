package com.gugu.demo.bug;

/**
 * @author Administrator
 * @Classname FinnalDemo
 * @Description TODO
 * @Date 2022/3/6 17:16
 */
public class FinnalDemo {
    static int value = 0;
    static int inc(){
        return value++;
    }

    static int dec(){
        return value--;
    }

    static int getResult(){
        try {
            return inc();
        }finally {
            return dec();
        }
    }

    public static void main(String[] args) {
        System.out.println(getResult());
        System.out.println(value);
    }
}
