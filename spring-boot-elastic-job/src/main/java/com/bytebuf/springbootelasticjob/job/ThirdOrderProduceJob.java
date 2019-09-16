package com.bytebuf.springbootelasticjob.job;

import com.bytebuf.autoconfig.ElasticSimpleJob;
import com.bytebuf.springbootelasticjob.service.OrderService;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 张新征
 * @date: 2019/9/17 6:39 上午
 */
@Slf4j
@ElasticSimpleJob(jobName = "thirdOrderProduceJob", cron = "0/5 * * * * ?", shardingTotalCount = 1, overwrite = true)
public class ThirdOrderProduceJob implements SimpleJob {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {
        orderService.produceThirdOrder();
    }
}
