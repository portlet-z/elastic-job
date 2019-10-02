package com.bytebuf.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

import java.time.LocalTime;

/**
 * @author: 张新征
 * @date: 2019/10/2 7:23 下午
 */
public class MyJobListener extends JobListenerSupport {
    @Override
    public String getName() {
        return "myJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        super.jobToBeExecuted(context);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "jobToBeExecuted");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        super.jobWasExecuted(context, jobException);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "jobWasExecuted");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        super.jobExecutionVetoed(context);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "jobExecutionVetoed");
    }
}
