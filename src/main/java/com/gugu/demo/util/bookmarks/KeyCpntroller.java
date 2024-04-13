package com.gugu.demo.util.bookmarks;

import lombok.SneakyThrows;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author gugu
 * @Classname KeyCpntroller
 * @Description TODO
 * @Date 2023/5/1 23:14
 */
public class KeyCpntroller {
    @SneakyThrows
    public static void main(String[] args) {
//        collect();
        select();
    }
    @SneakyThrows
    public static void select() {

        Robot r = new Robot();

            for (int i = 0; i < 100; i++) {
                r.delay(1000);
                r.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
                r.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
//                r.delay(50); 帐号已迁移
//                move( -2, r);
            }
    }
//    @SneakyThrows
//    public static void collect() {
//
//        Robot r = new Robot();
//
//        for (int j = 0; j < 10; j++) {
//            for (int i = 0; i < 7; i++) {
//                r.delay(500);
//                r.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
//                r.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
//
//                r.delay(500);
//                r.keyPress(KeyEvent.VK_ALT);
//                r.keyPress(KeyEvent.VK_TAB);
//                r.delay(50);
//                r.keyRelease(KeyEvent.VK_TAB);
//                r.keyRelease(KeyEvent.VK_ALT);
//
//                r.delay(50);
//                move(0, 1, r);
//            }
//            r.delay(5000);
//        }
//    }

    public static void move(int heigh, Robot robot) {    //鼠标移动函数
        try {
            robot.mouseWheel(heigh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
