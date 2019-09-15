package com.bytebuf.springbootelasticjob.job;

import com.bytebuf.autoconfig.ElasticSimpleJob;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 张新征
 * @date: 2019/9/15 9:59 下午
 */
@Slf4j
@ElasticSimpleJob(jobName = "mySimpleJob", cron = "0/10 * * * * ?", shardingTotalCount = 2, overwrite = true)
public class MySimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("我是分片项：" + shardingContext.getShardingItem() + ",总分片数是：" + shardingContext.getShardingTotalCount());
    }
}
