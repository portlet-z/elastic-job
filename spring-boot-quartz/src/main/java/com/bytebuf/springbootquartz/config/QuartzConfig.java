package com.bytebuf.springbootquartz.config;

import com.bytebuf.springbootquartz.job.MyJob;
import com.bytebuf.springbootquartz.listener.MyTriggerListener;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 张新征
 * @date: 2019/10/2 6:15 下午
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJobDetail() {
        JobDetail detail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob1", "group1")
                .storeDurably()
                .build();
        return detail;
    }

    @Bean
    public Trigger myTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withIdentity("trigger1", "group1")
                .forJob(myJobDetail())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever())
                .build();
        return trigger;
    }

    @Bean
    public TriggerListener myTriggerListener() {
        return new MyTriggerListener();
    }
}
