package com.gugu.demo.util.thread.unsafe;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.Executors.*;

/**
 * @author gugu
 * @Classname Test4
 * @Description TODO
 * @Date 2022/9/3 10:18
 */
public class UnsafeTest4 {
    private static Unsafe unsafe = null;
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        int NUM_OF_THREADS = 1000;
        int NUM_OF_INCREMENTS = 100000;


        Counter[] counters = new Counter[] {new CASCounter(unsafe), new StupidCounter(),new SyncCounter(), new LockCounter(), new AtomicCounter()};

        for (Counter counter : counters) {
            long before = System.currentTimeMillis();
            ExecutorService service = newFixedThreadPool(NUM_OF_THREADS);
            for (int i = 0; i < NUM_OF_THREADS; i++) {
                service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
            }
            service.shutdown();
            service.awaitTermination(1, TimeUnit.MINUTES);
            long after = System.currentTimeMillis();
            System.out.println(counter.getClass().getSimpleName() + " result: " + counter.getCounter());
            System.out.println(counter.getClass().getSimpleName() + " Time passed in ms:" + (after - before));
        }
    }
}

class StupidCounter implements Counter{
    private long counter = 0;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}

class SyncCounter implements Counter{
    private long counter = 0;

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}

class LockCounter implements Counter{
    private long counter = 0;
    private ReentrantReadWriteLock.WriteLock lock = new ReentrantReadWriteLock().writeLock();

    @Override
    public void increment() {
        lock.lock();
        counter++;
        lock.unlock();
    }

    @Override
    public long getCounter() {
        return counter;
    }
}

class AtomicCounter implements Counter{
    AtomicLong counter = new AtomicLong(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public long getCounter() {
        return counter.get();
    }
}

class CASCounter implements Counter{
    private volatile long counter = 0;
    private static Unsafe unsafe = null;
    private long offset;

    @SneakyThrows
    public CASCounter(Unsafe unsafe) {
        CASCounter.unsafe = unsafe;
        offset = CASCounter.unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }

    @Override
    public void increment() {
        long before = counter;
        while (!CASCounter.unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
            before = counter;
        }
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
