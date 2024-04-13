package com.gugu.demo.util.thread.pool;

import com.gugu.demo.util.thread.pool.communication.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gugu
 * @Classname CustomThreadPool
 * @Description 自定义线程池
 * @Date 2022/9/11 21:46
 */
public class CustomThreadPool {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomThreadPool.class);
    private final ReentrantLock lock = new ReentrantLock();
    /**
     * @Description 最小线程数，也叫核心线程数
     */
    private volatile int miniSize;
    /**
     * @Description 最大线程数
     */
    private volatile int maxSize;
    /**
     * @Description 线程需要被回收的时间
     */
    private long keepAliveTime;
    private TimeUnit unit;
    /**
     * @Description 存放线程的阻塞队列
     */
    private BlockingQueue<Runnable> workQueue;
    /**
     * @Description 存放线程池
     */
    private volatile Set<Worker> workers;
    /**
     * @Description 是否关闭线程池标志
     */
    private AtomicBoolean isShutDown = new AtomicBoolean(false);
    /**
     * @Description 提交到线程池中的任务总数
     */
    private AtomicInteger totalTask = new AtomicInteger();
    /**
     * 线程池任务全部执行完毕后的通知组件
     */
    private Object shutDownNotify = new Object();

    private Notify notify;

    /**
     * @Description
     * @params 
     * @param miniSize 最小线程数
     * @param maxSize 最大线程数
     * @param keepAliveTime 线程保活时间
     * @param unit 
     * @param workQueue 阻塞队列
     * @param notify 通知接口
     * @return 
     * @auther gugu
     */
    public CustomThreadPool(int miniSize, int maxSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, Notify notify) {
        this.miniSize = miniSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
        this.notify = notify;

        workers = new ConcurrentHashSet<>();
    }
    /**
     * 有返回值
     *
     * @param callable
     * @param <T>
     * @return
     */
    public <T> Future<T> submit(Callable<T> callable) {
        FutureTask<T> future = new FutureTask(callable);
        execute(future);
        return future;
    }

    /**
     * @Description 执行任务
     * @params 
     * @param runnable runnable 需要执行的任务
     * @return void
     * @auther gugu
     */
    public void execute(Runnable runnable){
        if (runnable == null){
            throw new NullPointerException("runnable nullPointerException");
        }

        if (isShutDown.get()){
            LOGGER.info("线程池已经关闭，不能再提交任务！");
            return;
        }

        //提交的线程 计数
        totalTask.incrementAndGet();

        //小于最小线程数时新建线程
        if (workers.size() < miniSize){
            addWorker(runnable);
            return;
        }

        boolean offer = workQueue.offer(runnable);
        //写入队列失败
        if(!offer){
            //创建新的线程执行
            if (workers.size() < maxSize){
                addWorker(runnable);
                return;
            }else {
                LOGGER.error("超过最大线程数");
                try {
                    //会阻塞
                    workQueue.put(runnable);
                } catch (InterruptedException e) {
                }
            }
        }
    }
    /**
     * @Description 添加任务，需要加锁
     * @params 
     * @param runnable runnable 任务
     * @return void
     * @auther gugu
     */
    private void addWorker(Runnable runnable) {
        Worker worker = new Worker(runnable, true);
        worker.startTask();
        workers.add(worker);
    }

    /**
     * 获取工作线程数量
     *
     * @return
     */
    public int getWorkerCount() {
        return workers.size();
    }

    /**
     * 阻塞等到任务执行完毕
     */
    public void mainNotify() {
        synchronized (shutDownNotify) {
            while (totalTask.get() > 0) {
                try {
                    shutDownNotify.wait();
                    if (notify != null) {
                        notify.notifyListen();
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }



    /**
     * @Description 工作线程
     * @auther gugu
     */
    protected final class Worker extends Thread{
        private Runnable task;
        private Thread thread;

        /**
         * true --> 创建新的线程执行
         * false --> 从队列里获取线程执行
         */
        private boolean isNewTask;

        public Worker(Runnable task, boolean isNewTask){
            this.task = task;
            this.isNewTask = isNewTask;
            thread = this;
        }

        public void startTask(){
            thread.start();
        }

        public void close(){
            thread.interrupt();
        }

        @Override
        public void run() {
            Runnable task = null;
            if (isNewTask){
                task = this.task;
            }
            boolean compile = true ;

            try {
                while (task != null || (task = getTask()) != null){
                    try {
                        // 执行
                        task.run();
                    }catch (Exception e){
                        compile = false;
                        throw e;
                    } finally {
                        // 任务执行完
                        task = null;
                        int number = totalTask.decrementAndGet();
                        if (number == 0) {
                            synchronized (shutDownNotify){
                                shutDownNotify.notify();
                            }
                        }
                    }
                }
            } finally {
                // 释放线程
                boolean remove = workers.remove(this);

                if (!compile){
                    addWorker(null);
                }
                tryClose(true);
            }
        }

        /**
         * @Description 从队列中获取任务
         */
        private Runnable getTask(){
            //关闭标识及任务是否全部完成
            if (isShutDown.get() && totalTask.get() == 0){
                return null;
            }
            lock.lock();
            try {
                Runnable task = null;
                if (workers.size() > miniSize) {
                    //大于核心线程数时需要用保活时间获取任务
                    task = workQueue.poll(keepAliveTime, unit);
                } else {
                    task = workQueue.take();
                }

                if (task != null){
                    return task;
                }
            } catch (InterruptedException e) {
                return null;
            } finally {
                lock.unlock();
            }
            return null;
        }
    }

    public void shutdown() {
        isShutDown.set(true);
        tryClose(true);
    }

    /**
     * @Description 立即关闭线程池，会造成任务丢失
     */
    public void shutDownNow() {
        isShutDown.set(true);
        tryClose(false);
    }

    /**
     * 关闭线程池
     *
     * @param isTry true 尝试关闭      --> 会等待所有任务执行完毕
     *              false 立即关闭线程池--> 任务有丢失的可能
     */
    private void tryClose(boolean isTry){
        if (!isTry){
            closeAllTask();
        } else {
            if (isShutDown.get() && totalTask.get() == 0){
                closeAllTask();
            }
        }
    }

    /**
     * 关闭所有任务
     */
    private void closeAllTask() {
        for (Worker worker : workers) {
            //LOGGER.info("开始关闭");
            worker.close();
        }

    }

    /**
     * @Description 内部存放工作线程容器，并发安全
     * @auther gugu
     */
    private final class ConcurrentHashSet<T> extends AbstractSet<T> {
        private ConcurrentHashMap<T, Object> map = new ConcurrentHashMap<>();
        private final Object PRESENT = new Object();
        private AtomicInteger count = new AtomicInteger();

        @Override
        public Iterator<T> iterator() {
            return map.keySet().iterator();
        }

        @Override
        public boolean add(T t) {
            count.incrementAndGet();
            return map.put(t, PRESENT) == null;
        }

        @Override
        public boolean remove(Object o) {
            count.decrementAndGet();
            return map.remove(o) == PRESENT;
        }

        @Override
        public int size() {
            return count.get();
        }
    }
}
