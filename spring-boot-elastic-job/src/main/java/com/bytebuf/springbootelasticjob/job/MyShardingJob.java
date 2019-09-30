package com.bytebuf.springbootelasticjob.job;

import com.bytebuf.autoconfig.ElasticSimpleJob;
import com.bytebuf.autoconfig.sharding.MyShardingStrategy;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 张新征
 * @date: 2019/9/24 11:41 下午
 */
@ElasticSimpleJob(
        jobName = "myShardingJob",
        cron = "0/10 * * * * ?",
        overwrite = true,
        shardingTotalCount = 10,
        jobStrategy = MyShardingStrategy.class,
        jobEvent = true
)
@Slf4j
public class MyShardingJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("我是分片项：" + shardingContext.getShardingItem());
    }
}
