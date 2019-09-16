package com.bytebuf.springbootelasticjob.service;

import com.bytebuf.springbootelasticjob.dao.AllOrderMapper;
import com.bytebuf.springbootelasticjob.dao.JdOrderMapper;
import com.bytebuf.springbootelasticjob.dao.OrderMapper;
import com.bytebuf.springbootelasticjob.dao.TmallOrderMapper;
import com.bytebuf.springbootelasticjob.model.AllOrder;
import com.bytebuf.springbootelasticjob.model.JdOrder;
import com.bytebuf.springbootelasticjob.model.Order;
import com.bytebuf.springbootelasticjob.model.TmallOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: 张新征
 * @date: 2019/9/16 8:05 上午
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private JdOrderMapper jdOrderMapper;

    @Autowired
    private TmallOrderMapper tmallOrderMapper;

    @Autowired
    private AllOrderMapper allOrderMapper;

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

    public void cancelOrder(Integer orderId, Date updateTime, int status, String updateUser, Date updateNow) {
        orderMapper.cancelOrder(orderId, updateTime, status, updateUser, updateNow);
    }

    public void produceThirdOrder() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int randomInt = random.nextInt(3);
            // jd
            if (randomInt == 0) {
                log.info("插入京东订单");
                JdOrder jdOrder = new JdOrder();
                jdOrder.setStatus(0);
                jdOrder.setAmount(BigDecimal.TEN);
                jdOrder.setCreateUser("jdUser");
                jdOrder.setCreateTime(new Date());
                jdOrder.setUpdateTime(new Date());
                jdOrder.setUpdateUser("jdUser");
                jdOrderMapper.insertSelective(jdOrder);
            } else {
                log.info("插入天猫订单");
                TmallOrder tmallOrder = new TmallOrder();
                tmallOrder.setOrderStatus(0);
                tmallOrder.setMoney(BigDecimal.valueOf(100));
                tmallOrder.setCreateUser("tmallUser");
                tmallOrder.setCreateTime(new Date());
                tmallOrder.setUpdateUser("tmallUser");
                tmallOrder.setUpdateTime(new Date());
                tmallOrderMapper.insertSelective(tmallOrder);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void processJdOrder(AllOrder allOrder) {
        allOrderMapper.insertSelective(allOrder);
        JdOrder jdOrder = new JdOrder();
        jdOrder.setId(allOrder.getThirdOrderId());
        jdOrder.setUpdateUser("sys");
        jdOrder.setUpdateTime(new Date());
        jdOrder.setStatus(1);
        jdOrderMapper.updateByPrimaryKeySelective(jdOrder);
    }

    @Transactional(rollbackFor = Exception.class)
    public void processTmallOrder(AllOrder allOrder) {
        allOrderMapper.insertSelective(allOrder);
        TmallOrder tmallOrder = new TmallOrder();
        tmallOrder.setId(allOrder.getThirdOrderId());
        tmallOrder.setUpdateUser("sys");
        tmallOrder.setUpdateTime(new Date());
        tmallOrder.setOrderStatus(1);
        tmallOrderMapper.updateByPrimaryKeySelective(tmallOrder);
    }
}
