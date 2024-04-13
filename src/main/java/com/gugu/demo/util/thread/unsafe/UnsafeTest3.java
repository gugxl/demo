package com.gugu.demo.util.thread.unsafe;

import lombok.SneakyThrows;

import static com.gugu.demo.util.thread.unsafe.UnsafeTest2.getUnsafe;


/**
 * @author gugu
 * @Classname UnsafeTest3
 * @Description 浅拷贝
 * @Date 2022/8/29 23:13
 */
public class UnsafeTest3 {
    public static void main(String[] args) {
        long sum = 0;
        long SUPER_SIZE = (long)Integer.MAX_VALUE * 2;
        SuperArray array = new SuperArray(SUPER_SIZE);
        System.out.println("Array size:" + array.size()); // 4294967294
        for (int i = 0; i < 100; i++) {
            array.set((long)Integer.MAX_VALUE + i, (byte)3);
            sum += array.get((long)Integer.MAX_VALUE + i);
        }
        System.out.println("Sum of 100 elements:" + sum);  // 300
    }

}

class SuperArray {
    private final static int BYTE = 1;

    private final long size;
    private final long address;

    public SuperArray(long size) {
        this.size = size;
        address = getUnsafe().allocateMemory(size * BYTE);
    }

    public void set(long i, byte value) {
        getUnsafe().putByte(address + i * BYTE, value);
    }

    public int get(long idx) {
        return getUnsafe().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }
}