package com.bytebuf.springbootelasticjob.job;

import com.bytebuf.springbootelasticjob.model.Order;
import com.bytebuf.springbootelasticjob.service.OrderService;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

/**
 * @author: 张新征
 * @date: 2019/9/15 9:59 下午
 */
@Slf4j
//@ElasticSimpleJob(jobName = "mySimpleJob", cron = "0/10 * * * * ?", shardingTotalCount = 1, overwrite = true)
public class MySimpleJob implements SimpleJob {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, -30);
        //订单尾号 % 分片总数 == 当前分片项
        List<Order> orders = orderService.getOrder(now, shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
    }
}
