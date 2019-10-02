package com.bytebuf.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import java.time.LocalTime;

/**
 * @author: 张新征
 * @date: 2019/10/2 7:00 下午
 */
public class MyTriggerListener extends TriggerListenerSupport {
    @Override
    public String getName() {
        return "myTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        super.triggerFired(trigger, context);
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + " Trigger is fired");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        super.triggerComplete(trigger, context, triggerInstructionCode);
        String string = triggerInstructionCode.toString();
        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + " Trigger is completed " + string);
    }
}
