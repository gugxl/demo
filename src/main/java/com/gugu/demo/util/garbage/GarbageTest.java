package com.gugu.demo.util.garbage;

import java.io.IOException;

/**
 * @author Administrator
 * @Classname GarbageTest
 * @Description TODO
 * @Date 2021/10/17 11:40
 */
public class GarbageTest {
    public static void main(String[] args) throws IOException {
        try {
            gcTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("has exited gcTest!");

        System.in.read();
        System.in.read();
        System.out.println("out begingc!");
        for(int i=0;i<100;i++)
        {
            System.gc();
            System.in.read();
            System.in.read();
        }

    }

    public static void gcTest() throws IOException {
        System.in.read();
        System.in.read();

        Person person = new Person();

        System.in.read();
        System.in.read();

        Person person2 = new Person();

        person.setMate(person2);
        person2.setMate(person);

        System.out.println("before exit gctest!");

        System.in.read();
        System.in.read();
        System.gc();
        System.out.println("exit gctest!");
    }

    private static class Person{
        byte[] data =new byte[20000000];
        Person mate = null;
        public void setMate(Person mate) {
            this.mate = mate;
        }
    }
}
