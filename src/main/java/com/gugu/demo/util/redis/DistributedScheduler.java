package com.gugu.demo.util.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 每个进程的内存中都会保存所有的任务实例 到点就会运行相应的任务
 * 通过 Redis 的分布式锁来控制互斥（同一时间只允许单个进程运行任务）
 * 调度器是单一的线程
 * 任务运行器是一个线程池，默认大小为 cores * 2
 * 利用全局版本号来控制任务重加载（变更的任务）
 *
 * @author Administrator
 * @Classname DistributedScheduler
 * @Description TODO
 * @Date 2021/11/21 14:46
 */
public class DistributedScheduler {
    // 任务触发器存储器，持久化
    private ITaskStore store;

    // 任务列表版本号（任务重加载）
    private long version;

    // 所有的任务
    private Map<String, Task> allTasks = new HashMap<>();

    // 任务触发器
    private Map<String, Trigger> triggers = new HashMap<>();

    // 任务调度器（调度线程）
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    // 任务运行器（运行线程）
    private ExecutorService executor;

    // 任务完成监听器
    private List<ISchedulerListener> listeners = new ArrayList<>();

    // 待重加载的触发器
    private Map<String, Trigger> reloadingTriggers = new HashMap<>();

    public DistributedScheduler(ITaskStore store) {
        this(store, Runtime.getRuntime().availableProcessors() * 2);
    }

    /**
     * @Description TODO
     * @params
     * @param store 任务存储器
     * @param threads 任务运行线程数（默认 cores*2）
     * @return
     * @auther Administrator
     */
    public DistributedScheduler(ITaskStore store, int threads) {
        this.store = store;
        this.executor = Executors.newFixedThreadPool(threads);
    }

    /**
     * @Description 注册调度监听器
     * @params
     * @param listener
     * @return com.gugu.demo.redis.DistributedScheduler
     * @auther Administrator
     */

    public DistributedScheduler listener(ISchedulerListener listener){
        this.listeners = listeners;
        return this;
    }

    /**
     * @Description 注册任务
     * @params 
     * @param trigger
     * @param task
     * @return com.gugu.demo.redis.DistributedScheduler
     * @auther Administrator
     */
    
    public DistributedScheduler register(Trigger trigger, Task task){
        if (this.triggers.containsKey(task.getName())){
            throw new IllegalArgumentException("task name duplicated!");
        }

        this.triggers.put(task.getName(), trigger);
        this.allTasks.put(task.getName(), task);
        task.callback(ctx -> {
            for (ISchedulerListener listener : listeners) {
                try {
                    listener.onComplete(ctx);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return this;
    }

    /**
     * @Description 手动触发任务运行
     * @params 
     * @param name
     * @return void
     * @auther Administrator
     */
    
    public void triggerTask(String name){
        Task task = this.allTasks.get(name);
        if (null != task){
            task.run();
        }
    }

    /**
     * @Description 任务变更时，必须递增版本号，才能触发其它进程的任务重新加载
     * @params 
     * @param version
     * @return com.gugu.demo.redis.DistributedScheduler
     * @auther Administrator
     */
    
    public DistributedScheduler version(int version){
        if (version < 0){
            throw new IllegalArgumentException("tasks version must be non-negative!");
        }
        this.version = version;

        return this;
    }

    /**
     * @Description 启动调度器
     * @params
     * @return void
     * @auther Administrator
     */
    public void start() {
        // 先保存触发器（如果任务变更，会触发其它进程重加载）
        this.saveTriggers();
        // 调度任务
        this.scheduleTasks();
        // 监控任务版本（重加载）
        this.scheduleReload();

        // 回调
        for (ISchedulerListener listener : listeners) {
            try {
                listener.onStartup();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description TODO
     * @params
     * @return void
     * @auther Administrator
     */
    
    private void saveTriggers(){
        Map<String, String> triggersRaw  = new HashMap<>();
        this.triggers.forEach((name, trigger) -> {
            triggersRaw.put(name, trigger.s());
        } );
        this.store.saveAllTriggers(version, triggersRaw);
    }

    private void scheduleTasks(){
        this.triggers.forEach((name, trigger) -> {
            Task task = allTasks.get(name);
            if (null == task){
                return;
            }
            System.out.println("scheduling task "+ name);
            trigger.schedule(scheduler, executor, this::grabTaskSilently, task);
        });
    }
    private boolean grabTaskSilently(Task task){
        if (task.isConcurrent()){
            return true;
        }
        try {
            return store.grabTask(task.getName());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void scheduleReload(){
        // 1s 对比一次
        this.scheduler.scheduleWithFixedDelay(() -> {
            try {
                if (this.reloadIfChanged()){
                    this.rescheduleTasks();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }, 0 , 1 , TimeUnit.SECONDS);
    }

    private void rescheduleTasks() {
        this.reloadingTriggers.forEach((name, trigger) -> {
            Task task = this.allTasks.get(name);
            if (trigger == null){
                // deleting
                System.out.println("unscheduling task " + name);
                triggers.get(name).cancel();
            } else {
                Trigger oldTrigger = triggers.get(name);
                if (null != oldTrigger){
                    // updating, cancel the old first
                    System.out.println("unscheduling task " + name);
                    oldTrigger.cancel();
                }

                triggers.put(name, trigger);
                // new
                System.out.println("scheduling task " + name);
                trigger.schedule(scheduler, executor, this::grabTaskSilently, task);
            }
        });

        this.reloadingTriggers.clear();
        // 回调
        for (ISchedulerListener listener : listeners) {
            try {
                listener.onReload();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.cancelAllTasks();
        this.scheduler.shutdown();

        try {
            this.scheduler.awaitTermination(1 , TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.executor.shutdown();

        try {
            if (! this.executor.awaitTermination(10, TimeUnit.SECONDS)){
                System.out.println("work is not complete while stopping scheduler");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 回调
        for (ISchedulerListener listener : listeners) {
            try {
                listener.onStop();
            } catch (Exception e) {
                System.out.println("invoke scheduler stop listener error");
            }
        }

    }

    private synchronized  void cancelAllTasks(){
        this.triggers.forEach((name, trigger) -> {
            System.out.println("cancelling task" + name);
            trigger.cancel();
        });

        this.triggers.clear();
    }

    private boolean reloadIfChanged() {
        long remoteVersion = store.getRemoteVersion();
        if (remoteVersion != version){
            this.version = remoteVersion;
            System.err.println("version changed! reload triggers then reschedule changed tasks");
            this.reload();
            return true;
        }
        return false;
    }

    private void reload() {
        Map<String, String> raws = store.getAllTriggers();
        Map<String, Trigger> reloadings  = new HashMap<>();
        raws.forEach( (name, raw) -> {
            // 内存里必须有这个任务（新增任务，老版本的进程里就没有）
            if (this.allTasks.containsKey(name)){
                Trigger trigger  = Trigger.build(raw);
                Trigger oldTrigger = this.triggers.get(name);
                if (null == oldTrigger || !oldTrigger.equals(trigger)){
                    // new or changed
                    reloadings.put(name, trigger);
                }
            }
        });
        // deleted
        this.triggers.forEach((name, trigger) -> {
            if (!raws.containsKey(name)){
                reloadings.put(name, null);
            }
        });
        this.reloadingTriggers = reloadings;
    }

}
