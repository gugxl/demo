package com.gugu.demo.util.thread.unsafe;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;


/**
 * @author gugu
 * @Classname UnsafeTest2
 * @Description 内存泄漏
 * @Date 2022/8/29 22:07
 */
public class UnsafeTest2 {
    @SneakyThrows
    public static Unsafe getUnsafe(){
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    // 直接改变对象内容
    @SneakyThrows
    public static void main(String[] args) {
        Guard guard = new Guard();
        Unsafe unsafe = getUnsafe();
        Field access_allowed = Guard.class.getDeclaredField("ACCESS_ALLOWED");

        System.out.println(sizeOf(guard));
        unsafe.putInt(guard,  unsafe.objectFieldOffset(access_allowed), 42); // memory corruption
        System.out.println(guard.giveAccess());
    }

    // 改变下个对象的内容
    @SneakyThrows
    public static void main2(String[] args) {
        Guard guard = new Guard();
        Guard guard2 = new Guard();
        Unsafe unsafe = getUnsafe();
        Field access_allowed = Guard.class.getDeclaredField("ACCESS_ALLOWED");
        // 16 是计算出来的
        unsafe.putInt(guard, 16 + unsafe.objectFieldOffset(access_allowed), 42); // memory corruption
        System.out.println(guard2.giveAccess());
    }

    public static long sizeOf(Object o) {
        Unsafe u = getUnsafe();
        HashSet<Field> fields = new HashSet<Field>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }
        return ((maxSize/8) + 1) * 8;   // padding
    }
}

class Guard{
    private int ACCESS_ALLOWED = 1;
    public boolean giveAccess(){
        return 42 == ACCESS_ALLOWED;
    }
}