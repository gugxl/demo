package com.gugu.demo.util.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Administrator
 * @Classname ForkJoinTask
 * @Description TODO
 * @Date 2022/6/2 21:52
 */
public class ForkJoinTask extends RecursiveTask<Long> {
    private static final long MAX = 1000000000L;
    private static final long THRESHOLD = 1000L;
    private long start;
    private long end;

    public static void main(String[] args) {
        test();
        System.out.println("---------------");
        testForkJoin();
    }




    public static void test() {
        System.out.println("test");
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (int i = 0; i <= MAX; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    public static void testForkJoin() {
        System.out.println("testForkJoin");
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long sum = forkJoinPool.invoke(new ForkJoinTask(1, MAX));
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    @Override
    protected Long compute() {
        Long sum = 0L;
        if (end - start <= THRESHOLD){
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long mid = (start + end) / 2;
            ForkJoinTask task1 = new ForkJoinTask(start, mid);
            task1.fork();
            ForkJoinTask task2 = new ForkJoinTask(mid + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }

    public ForkJoinTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

}
