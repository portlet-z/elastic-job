package com.bytebuf.job;

import com.bytebuf.model.Order;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 张新征
 * @date: 2019-09-15 15:40
 */
public class MyDataFlowJob implements DataflowJob<Order> {

    private List<Order> orders = new ArrayList<>();

    {
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setId(i+1);
            //未处理
            order.setStatus(0);
            orders.add(order);
        }
    }

    @Override
    public List<Order> fetchData(ShardingContext shardingContext) {
        //订单号 % 分片总数 == 当前分片项
        List<Order> orderList = orders.stream().filter(o -> o.getStatus() == 0)
                .filter(o -> o.getId() % shardingContext.getShardingTotalCount() == shardingContext.getShardingItem())
                .collect(Collectors.toList());
        List<Order> subList = null;
        if (orderList != null && orderList.size() > 0) {
            subList = orderList.subList(0, 10);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime time = LocalTime.now();
        System.out.println(time + ",我是分片项：" + shardingContext.getShardingItem() + ",我抓取的数据是：" + subList);
        return subList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Order> list) {
        list.forEach(o -> o.setStatus(1));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime time = LocalTime.now();
        System.out.println(time + ",我是分片项：" + shardingContext.getShardingItem() + ",我正在处理数据！");
    }
}
