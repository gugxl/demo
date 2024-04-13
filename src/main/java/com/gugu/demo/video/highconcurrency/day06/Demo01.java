package com.gugu.demo.video.highconcurrency.day06;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 *
 * https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648933082&idx=1&sn=e940c4f94a8c1527b6107930eefdcd00&chksm=88621ae4bf1593f270991e6f6bac5769ea850fa02f11552d1aa91725f4512d4f1ff8f18fcdf3&cur_album_id=1318984626890915841&scene=189#wechat_redirect
 */
public class Demo01 {
    // volatile 一定要加保证可见性
    static volatile boolean isStop = false;

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("run");
                    if (isStop){
                        System.out.println("退出");
                        break;
                    }
                }
            }
        }.start();
        TimeUnit.SECONDS.sleep(1);
        isStop=true;

    }
}
