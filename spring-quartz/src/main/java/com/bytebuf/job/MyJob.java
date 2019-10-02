package com.bytebuf.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalTime;

/**
 * @author: 张新征
 * @date: 2019/10/2 10:27 上午
 */
public class MyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString() + ",MyJob1我正在执行");
    }
}
