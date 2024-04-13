package com.gugu.demo.util.thread.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author gugu
 * @Classname Test
 * @Description TODO
 * @Date 2022/9/11 22:49
 */
public class CustomThreadPoolTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomThreadPoolTest.class);

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue queue = new ArrayBlockingQueue<>(10 );
        CustomThreadPool customThreadPool = new CustomThreadPool(3, 5, 1, TimeUnit.SECONDS, queue, null);
        for (int i = 0; i < 10; i++) {
            customThreadPool.execute(new Worker(i));
        }
        LOGGER.info("=======休眠前线程池活跃线程数={}======",customThreadPool.getWorkerCount());
        TimeUnit.SECONDS.sleep(5);
        LOGGER.info("=======休眠后线程池活跃线程数={}======",customThreadPool.getWorkerCount());
        for (int i = 0; i < 3; i++) {
            customThreadPool.execute(new Worker(i + 100));
        }
        customThreadPool.shutdown();
//        customThreadPool.shutDownNow();
        //customThreadPool.execute(new Worker(100));
        LOGGER.info("++++++++++++++");
//        customThreadPool.mainNotify();
    }

    private static class Worker implements Runnable{

        private int state ;

        public Worker(int state) {
            this.state = state;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                LOGGER.info("state={}",state);
            } catch (InterruptedException e) {

            }
        }
    }
}
