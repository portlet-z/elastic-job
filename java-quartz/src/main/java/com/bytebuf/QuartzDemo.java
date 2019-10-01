package com.bytebuf;

import com.bytebuf.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author: 张新征
 * @date: 2019/10/1 3:00 上午
 */
public class QuartzDemo {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("jobDetail1", "group")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(10)
                        .repeatForever()
                ).build();

        scheduler.scheduleJob(jobDetail, trigger);

        try {
            Thread.sleep(600 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}
