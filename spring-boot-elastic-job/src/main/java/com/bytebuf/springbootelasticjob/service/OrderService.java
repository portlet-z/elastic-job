package com.bytebuf.springbootelasticjob.service;

import com.bytebuf.springbootelasticjob.dao.OrderMapper;
import com.bytebuf.springbootelasticjob.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: 张新征
 * @date: 2019/9/16 8:05 上午
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public int insertOrder() {
        Order order = new Order();
        order.setAmount(BigDecimal.TEN);
        order.setReceiveName("Green");
        order.setReceiveAddress("朝阳");
        order.setReceiveMobile("12345678910");
        order.setStatus(1);
        order.setCreateUser("sys");
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setUpdateUser("sys");
        return orderMapper.insert(order);
    }

    public List<Order> getOrder(Calendar now, int shardingTotalCount, int shardingItem) {
        return orderMapper.getOrder(now.getTime(), shardingTotalCount, shardingItem);
    }
}
