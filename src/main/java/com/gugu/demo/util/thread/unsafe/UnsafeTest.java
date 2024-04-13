package com.gugu.demo.util.thread.unsafe;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author gugu
 * @Classname UnsafeTest
 * @Description 跳过构造方法创建对象
 * @Date 2022/8/29 21:20
 */
public class UnsafeTest {
    @SneakyThrows
    public static void main(String[] args)   {
        // 不能通过简单的new 和 getUnsafe() 会抛异常 可以通过反射
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        A a1 = new A(); // constructor
        System.out.println(a1.a()); // prints 1

        A a2 = A.class.newInstance(); // reflection
        System.out.println(a2.a()); // prints 1

        A a3 = (A) unsafe.allocateInstance(A.class); // unsafe
        System.out.println(a3.a()); // prints 0
    }
}

class A{
    private long a;// not initialized value

    public A() {
        this.a = 1; // initialization
    }
    public long a() {
        return a;
    }
}