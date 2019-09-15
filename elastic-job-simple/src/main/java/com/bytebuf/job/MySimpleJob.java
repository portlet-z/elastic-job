package com.bytebuf.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author: 张新征
 * @date: 2019-09-15 15:16
 */
public class MySimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("我是分片项：" + shardingContext.getShardingItem() + ",总分片项：" + shardingContext.getShardingTotalCount());
    }
}
