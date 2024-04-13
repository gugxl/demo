package com.gugu.demo.util.lock;

/**
 * @author gugu
 * @Classname Test
 * @Description TODO
 * @Date 2022/9/4 20:47
 */
public class Test {
    public static void main(String[] args) {
        WeakHashLock<String> stringSegmentLock = new WeakHashLock<>();
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");
        stringSegmentLock.get("gugu");


    }
}
