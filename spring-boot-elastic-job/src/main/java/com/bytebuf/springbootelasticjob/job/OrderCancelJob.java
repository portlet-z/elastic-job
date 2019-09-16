package com.bytebuf.springbootelasticjob.job;

import com.bytebuf.autoconfig.ElasticSimpleJob;
import com.bytebuf.springbootelasticjob.model.Order;
import com.bytebuf.springbootelasticjob.service.OrderService;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: 张新征
 * @date: 2019/9/15 9:59 下午
 */
@Slf4j
@ElasticSimpleJob(jobName = "orderCancelJob", cron = "0/15 * * * * ?", shardingTotalCount = 2, overwrite = true)
public class OrderCancelJob implements SimpleJob {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, -30);
        //订单号 % 分片总数 == 当前分片项
        List<Order> orders = orderService.getOrder(now, shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());

        if (orders != null && orders.size() > 0) {
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            for (Order order : orders) {
                executorService.execute(() -> {
                    Integer orderId = order.getId();
                    Date updateTime = order.getUpdateTime();
                    //更新内容
                    int status = 3;
                    String updateUser = "sys";
                    Date updateNow = new Date();
                    orderService.cancelOrder(orderId, updateTime, status, updateUser, updateNow);
                });
            }
            executorService.shutdown();
        }
    }
}
