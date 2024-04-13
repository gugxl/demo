package com.gugu.demo.util.redis;

import it.sauronsoftware.cron4j.SchedulingPattern;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author Administrator
 * @Classname CronTrigger
 * @Description TODO
 * @Date 2021/11/21 15:54
 */
public class CronTrigger implements Trigger {

    private String cronExpr;

    private ScheduledFuture<?> future;

    public CronTrigger() {
    }

    public CronTrigger(String cronExpr) {
        this.cronExpr = cronExpr;
    }

    public String getCronExpr() {
        return cronExpr;
    }

    @Override
    public TriggerType type() {
        return TriggerType.CRON;
    }

    @Override
    public void parse(String s) {
        this.cronExpr = s;
    }

    @Override
    public String serialize() {
        return this.cronExpr;
    }

    @Override
    public void cancel() {
        if (this.future != null){
            this.future.cancel(false);
        }
    }

    @Override
    public boolean schedule(ScheduledExecutorService scheduler, ExecutorService executor, Predicate<Task> taskGrabber, Task task) {
        SchedulingPattern pattern = new SchedulingPattern(this.getCronExpr());
        // 将毫秒数清零，确保多进程同一时间争抢
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        // 如果正好卡在分点上（second=0）那就立即执行
        if (cal.get(Calendar.SECOND) != 0){
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 1);
        }

        long delay = cal.getTimeInMillis() - System.currentTimeMillis();

        this.future  = scheduler.scheduleAtFixedRate(() -> {
            if (pattern.match(System.currentTimeMillis())){
                executor.submit(() -> {
                    if (taskGrabber.test(task)){
                        task.run();
                    }
                });
            }
        }, delay, 60 * 1000 , TimeUnit.MILLISECONDS);
        return this.future != null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CronTrigger)) return false;
        CronTrigger that = (CronTrigger) o;
        return Objects.equals(cronExpr, that.cronExpr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cronExpr);
    }
}
