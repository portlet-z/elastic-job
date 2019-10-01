package com.bytebuf.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * @author: 张新征
 * @date: 2019/10/1 3:00 上午
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalTime localTime = LocalTime.now();
        System.out.println("我正在执行" + localTime.toString());
    }
}
