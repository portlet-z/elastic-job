package com.bytebuf;

import com.bytebuf.job.MyJob;
import com.bytebuf.listener.MyJobListener;
import com.bytebuf.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Random;

/**
 * @author: 张新征
 * @date: 2019/10/1 3:00 上午
 */
public class QuartzDemo {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        Random random = new Random();
        int count = random.nextInt(10);

        JobDetail jobDetail1 = JobBuilder.newJob(MyJob.class)
                .withIdentity("jobDetail1", "group1")
                //.usingJobData("count", count)
                .build();

        JobDetail jobDetail2 = JobBuilder.newJob(MyJob.class)
                .withIdentity("jobDetail2", "group2")
                .build();

        Date date = DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND);

        Trigger trigger1 = TriggerBuilder.newTrigger()
                .startAt(date)
                .usingJobData("msg", "我是 trigger1触发的")
                .withPriority(2)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5)
                        .repeatForever()
                ).build();

        Trigger trigger2 = TriggerBuilder.newTrigger()
                .startAt(date)
                .usingJobData("msg", "我是 trigger2触发的")
                .withPriority(9)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever()
                ).build();

        scheduler.scheduleJob(jobDetail1, trigger1);
        scheduler.scheduleJob(jobDetail2, trigger2);

        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());
        scheduler.getListenerManager().addJobListener(new MyJobListener());

        try {
            Thread.sleep(600 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}
