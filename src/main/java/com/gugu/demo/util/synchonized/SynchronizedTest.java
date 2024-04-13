package com.gugu.demo.util.synchonized;

import lombok.SneakyThrows;

/**
 * @author Administrator
 * @Classname SynchronizedTest
 * @Description TODO
 * @Date 2021/11/6 9:21
 */
public class SynchronizedTest {
    SynchronizedTest LOCK = new SynchronizedTest();

    /**
     * @return void
     * @Description 用在普通方法, 同一个实例只有一个线程能获取锁进入这个方法。
     * @params
     * @auther gugu
     */

    @SneakyThrows
    public synchronized void synchronizedMethod() {
        System.out.println("synchronizedMethod");
        Thread.sleep(1000);

    }

    /**
     * @return void
     * @Description 同步静态方法，不管你有多少个类实例，同时只有一个线程能获取锁进入这个方法。
     * 同步静态方法是类级别的锁，一旦任何一个线程进入这个方法，其他所有线程将无法访问这个类的任何同步类锁的方法。
     * @params
     * @auther gugu
     */
    @SneakyThrows
    public synchronized static void synchronizedStaticMethod() {
        System.out.println("synchronizedStaticMethod");
        Thread.sleep(1000);
    }

    /**
     * @Description 用在类 方式1 锁住效果和同步静态方法一样，都是类级别的锁，同时只有一个线程能访问带有同步类锁的方法。
     * @params
     * @return void
     * @auther gugu
     */
    @SneakyThrows
    private void synchronizedClass1() {
        synchronized (SynchronizedTest.class) {
            System.out.println("synchronizedClass1");
            Thread.sleep(1000);
        }
    }
    // 方式2
    @SneakyThrows
    private void synchronizedClass2() {
        synchronized (this.getClass()) {
            System.out.println("synchronizedClass2");
            Thread.sleep(1000);
        }
    }

    /**
     * @Description 同步this实例, 这也是同步块的用法，表示锁住整个当前对象实例，只有获取到这个实例的锁才能进入这个方法。
     * @params
     * @return void
     * @auther gugu
     */
    @SneakyThrows
    public void synchronizedThis() {
        synchronized (this) {
            System.out.println("synchronizedClass2");
            Thread.sleep(1000);
        }
    }

    /**
     * @Description 这里表示锁住整个 LOCK 对象实例，只有获取到这个 LOCK 实例的锁才能进入这个方法。
     * @params
     * @return void
     * @auther gugu
     */
    @SneakyThrows
    public void synchronizedInstance(){
        synchronized (LOCK) {
            System.out.println("synchronizedInstance");
            Thread.sleep(1000);
        }
    }

}
