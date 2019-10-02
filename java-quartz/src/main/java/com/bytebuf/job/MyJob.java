package com.bytebuf.job;

import lombok.Getter;
import lombok.Setter;
import org.quartz.*;

import java.time.LocalTime;

/**
 * @author: 张新征
 * @date: 2019/10/1 3:00 上午
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class MyJob implements Job {

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private int count;

    @Setter
    @Getter
    private String msg;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LocalTime localTime = LocalTime.now();
        System.out.println(msg);
        //count++;
        //context.getJobDetail().getJobDataMap().put("count", count);
        //System.out.println(localTime.toString() + ",count = " + count);
//        System.out.println("下一次执行时间：" + context.getNextFireTime());
//        System.out.println("Trigger 的开始时间：" + context.getTrigger().getStartTime());
//        try {
//            System.out.println("GroupName:" + context.getScheduler().getJobGroupNames());
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        //String name = jobDataMap.getString("name");
//        System.out.println(name + "我正在执行" + localTime.toString());
//        System.out.println(Thread.currentThread().getName() + "开始执行" + localTime.toString());
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        LocalTime endTime = LocalTime.now();
//        System.out.println(Thread.currentThread().getName() + "结束执行" + endTime.toString());
    }
}
