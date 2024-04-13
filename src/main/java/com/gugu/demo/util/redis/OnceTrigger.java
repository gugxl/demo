package com.gugu.demo.util.redis;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author Administrator
 * @Classname OnceTrigger
 * @Description TODO
 * @Date 2021/11/21 15:36
 */
public class OnceTrigger implements Trigger {

    private Date startTime;

    private ScheduledFuture<?> future;

    public OnceTrigger() {
    }

    public OnceTrigger(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }


    @Override
    public TriggerType type() {
        return TriggerType.ONCE;
    }

    @Override
    public void parse(String s) {
        TimeFormat.SimplerDateFormat formatter  = TimeFormat.ISOFormatter.take();
        this.startTime = formatter.parseQuitely(s);
    }

    @Override
    public String serialize() {
        TimeFormat.SimplerDateFormat formatter = TimeFormat.ISOFormatter.take();
        return formatter.format(this.startTime);
    }

    @Override
    public void cancel() {
        if (this.future != null){
            this.future.cancel(false);
        }
    }

    @Override
    public boolean schedule(ScheduledExecutorService scheduler, ExecutorService executor, Predicate<Task> taskGrabber, Task task) {
        long delay = this.getStartTime().getTime() - System.currentTimeMillis();

        if (delay >= 0){
            this.future  = scheduler.schedule(() -> {
                executor.submit(() -> {
                    if(taskGrabber.test(task)){
                        task.run();
                    }
                });
            }, delay, TimeUnit.MILLISECONDS);
        }
        return this.future != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OnceTrigger)) {
            return false;
        }
        return this.startTime.equals(((OnceTrigger)obj).getStartTime().getTime());
    }
}
