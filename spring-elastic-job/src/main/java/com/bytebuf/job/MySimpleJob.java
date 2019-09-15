package com.bytebuf.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 张新征
 * @date: 2019-09-15 19:52
 */
@Slf4j
public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("我是分片项：" + shardingContext.getShardingItem());
        log.info("我是分片项：" + shardingContext.getShardingItem());
    }
}
