package com.gugu.demo.sugar;

/**
 * @author Administrator
 * @Classname ConditionalCompilation
 * @Description TODO
 * @Date 2022/2/19 17:11
 */
public class ConditionalCompilation {
    public static void main(String[] args) {
        final boolean DEBUG = true;
        if(DEBUG) {
            System.out.println("Hello, DEBUG!");
        }

        final boolean ONLINE = false;

        if(ONLINE){
            System.out.println("Hello, ONLINE!");
        }
    }
}
