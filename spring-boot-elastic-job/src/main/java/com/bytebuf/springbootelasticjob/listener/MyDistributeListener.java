package com.bytebuf.springbootelasticjob.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;

/**
 * @author: 张新征
 * @date: 2019/9/30 6:10 下午
 */
public class MyDistributeListener extends AbstractDistributeOnceElasticJobListener {

    public MyDistributeListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
        System.out.println("我是分布式监听器，我在方法执行前");
    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        System.out.println("我是分布式监听器，我在方法执行后");
    }
}
