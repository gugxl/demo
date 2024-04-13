package com.gugu.demo.util.redis;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author Administrator
 * @Classname PeriodTrigger
 * @Description TODO
 * @Date 2021/11/21 16:20
 */
public class PeriodTrigger implements Trigger {

    // 任务最早执行时间
    private Date startTime;
    // 任务最晚执行时间
    private Date endTime;
    // 间隔多少秒
    private int period;
    private ScheduledFuture<?> future;

    public PeriodTrigger() {
    }

    public PeriodTrigger(Date startTime, Date endTime, int period) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.period = period;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public TriggerType type() {
        return TriggerType.PERIOD;
    }

    @Override
    public void parse(String s) {
        TimeFormat.SimplerDateFormat formatter = TimeFormat.ISOFormatter.take();
        String[] parts = s.split("\\|");
        this.startTime = formatter.parseQuitely(parts[0]);
        this.endTime = formatter.parseQuitely(parts[1]);
        this.period = Integer.parseInt(parts[2]);
    }

    @Override
    public String serialize() {
        TimeFormat.SimplerDateFormat formatter = TimeFormat.ISOFormatter.take();
        StringJoiner stringJoiner = new StringJoiner("|");
        stringJoiner.add(formatter.format(this.getStartTime()));
        stringJoiner.add(formatter.format(this.getEndTime()));
        stringJoiner.add(String.valueOf(this.getPeriod()));
        return stringJoiner.toString();
    }

    @Override
    public void cancel() {
        if (this.future != null){
            this.future.cancel(false);
        }
    }

    @Override
    public boolean schedule(ScheduledExecutorService scheduler, ExecutorService executor, Predicate<Task> taskGrabber, Task task) {
        long now = System.currentTimeMillis();
        if (now >= this.getEndTime().getTime()){
            return false;
        }
        long delay = 0;
        if (now <= this.getStartTime().getTime()){
            delay = this.getStartTime().getTime() - now;
        } else {
            // 如果正好卡在周期点上那就立即执行
            // 否则延迟到下一个周期点
            long ellapsed = (now - this.getStartTime().getTime()) % (this.getPeriod() * 1000);
            if (ellapsed > 0){
                delay = this.getPeriod() * 1000 - ellapsed;
            }
        }

        this.future  = scheduler.scheduleAtFixedRate(() -> {
            // 到结束时间了，结束定时器
            if (System.currentTimeMillis() >= this.getEndTime().getTime()){
                cancel();
                return;
            }
            executor.submit(() -> {
                if (taskGrabber.test(task)){
                    task.run();
                }
            });
        }, delay, this.getPeriod() * 1000, TimeUnit.MILLISECONDS);
        return this.future != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodTrigger)) return false;
        PeriodTrigger that = (PeriodTrigger) o;
        return period == that.period &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, period);
    }
}
