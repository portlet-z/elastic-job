package com.bytebuf.springbootelasticjob.job;

import com.bytebuf.autoconfig.ElasticSimpleJob;
import com.bytebuf.springbootelasticjob.service.OrderService;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 张新征
 * @date: 2019/9/15 9:59 下午
 */
@Slf4j
@ElasticSimpleJob(jobName = "orderCancelJob", cron = "0/10 * * * * ?", shardingTotalCount = 1, overwrite = true)
public class OrderCancelJob implements SimpleJob {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {

    }
}
