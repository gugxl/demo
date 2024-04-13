package com.gugu.demo.util.redis;

import it.sauronsoftware.cron4j.SchedulingPattern;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Predicate;

/**
 * @author Administrator
 * @Classname Trigger
 * @Description TODO
 * @Date 2021/11/21 15:26
 */
public interface Trigger {
    TriggerType type();
    void parse(String s);
    String serialize();
    void cancel();

    public default String s() {
        return String.format("%s@%s", type().name(), serialize());
    }

    public boolean schedule(ScheduledExecutorService scheduler, ExecutorService executor,
                            Predicate<Task> taskGrabber, Task task);


    public static Trigger build(String s){
        String[] parts  = s.split("@", 2);
        String type = parts[0];
        Trigger trigger = null;

        switch (TriggerType.valueOf(type)){
            case ONCE:
                trigger = new OnceTrigger();
                break;
            case CRON:
                trigger = new CronTrigger();
                break;
            case PERIOD:
                trigger = new PeriodTrigger();
                break;
        }

        trigger.parse(parts[1]);

        return trigger;
    }

    static OnceTrigger once(Date startTime){
        return new OnceTrigger(startTime);
    }
    /**
     * @Description 不同进程调用这个函数得到的起始时间是不一样的，所以这个函数一般仅用于测试，它会导致任务在不同的进程中分配不均匀
     * @params
     * @param delay
     * @return com.gugu.demo.redis.OnceTrigger
     * @auther Administrator
     */

    public static OnceTrigger onceTrigger(int delay){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, delay);

        return once(cal.getTime());
    }

    public static PeriodTrigger period(Date startTime, Date endTime, int period){
        return new PeriodTrigger(startTime, endTime, period);
    }

    public static PeriodTrigger period(Date startTime, int period){
        return new PeriodTrigger(startTime, new Date(Integer.MAX_VALUE), period);
    }
    /**
     * @Description 不同进程调用这个函数得到的起始时间是不一样的，所以这个函数一般仅用于测试，它会导致任务在不同的进程中分配不均匀
     * @params
     * @param delay
     * @param period
     * @return com.gugu.demo.redis.PeriodTrigger
     * @auther Administrator
     */

    public static PeriodTrigger period(int delay, int period){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, delay);

        return new PeriodTrigger(cal.getTime(), new Date(Integer.MAX_VALUE), period);
    }

    public static CronTrigger cron(String expr){
        if (!SchedulingPattern.validate(expr)){
            throw new IllegalArgumentException("cron expression illegal");
        }
        return new CronTrigger(expr);
    }

    public static CronTrigger cronOfMinutes(int minutes ){
        return cron(String.format("*/%d * * * *", minutes));
    }

    public static CronTrigger cronOfHours(int hours, int minuteOffset) {
        return cron(String.format("*/%d */%d * * *", minuteOffset, hours));
    }
    public static CronTrigger cronOfDays(int days, int hourOffset, int minuteOffset) {
        return cron(String.format("*/%d */%d */%d * *", minuteOffset, hourOffset, days));
    }
}
