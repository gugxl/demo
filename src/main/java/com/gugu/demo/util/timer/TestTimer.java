package com.gugu.demo.util.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Administrator
 * @Classname TestTimer
 * @Description TODO
 * @Date 2022/4/17 16:36
 */
public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 0, 1000);
    }

    static class MyTimer extends TimerTask {

        @Override
        public void run() {
            System.out.println(new Date());
        }
    }
}
