package com.bytebuf.job;

import java.time.LocalTime;

/**
 * @author: 张新征
 * @date: 2019/10/2 10:36 上午
 */
public class MyJob2 {

    public static void execute() {
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + ",MyJob2我正在执行");
    }
}
