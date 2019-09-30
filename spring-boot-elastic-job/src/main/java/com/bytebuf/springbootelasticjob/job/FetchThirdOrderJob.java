package com.bytebuf.springbootelasticjob.job;

import com.bytebuf.springbootelasticjob.dao.JdOrderMapper;
import com.bytebuf.springbootelasticjob.dao.TmallOrderMapper;
import com.bytebuf.springbootelasticjob.model.AllOrder;
import com.bytebuf.springbootelasticjob.model.JdOrder;
import com.bytebuf.springbootelasticjob.model.TmallOrder;
import com.bytebuf.springbootelasticjob.service.OrderService;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 张新征
 * @date: 2019/9/17 6:53 上午
 */
//@ElasticDataFlowJob(jobName = "fetchThirdOrderJob", cron = "0/15 * * * * ?", shardingTotalCount = 2, overwrite = true, streamingProcess = true)
public class FetchThirdOrderJob implements DataflowJob<Object> {

    @Autowired
    private JdOrderMapper jdOrderMapper;

    @Autowired
    private TmallOrderMapper tmallOrderMapper;

    @Autowired
    private OrderService orderService;

    @Override
    public List<Object> fetchData(ShardingContext shardingContext) {

        //京东订单
        if (shardingContext.getShardingItem() == 0) {
            List<JdOrder> jdOrders = jdOrderMapper.getNotFetchedOrder(5);
            if (jdOrders != null && jdOrders.size() > 0) {
                List<Object> jdOrderList = jdOrders.stream().map(jdOrder -> (Object) jdOrder).collect(Collectors.toList());
                return jdOrderList;
            }
        } else {
            List<TmallOrder> tmallOrders = tmallOrderMapper.getNotFetchedOrder(5);
            if (tmallOrders != null && tmallOrders.size() > 0) {
                List<Object> tmallOrderList = tmallOrders.stream().map(tmallOrder -> (Object) tmallOrder).collect(Collectors.toList());
                return tmallOrderList;
            }
        }

        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Object> data) {
        if (shardingContext.getShardingItem() == 0) {
            if (data != null && data.size() > 0) {
                List<JdOrder> jdOrders = data.stream().map(d -> (JdOrder) d).collect(Collectors.toList());
                for (JdOrder jdOrder : jdOrders) {
                    AllOrder allOrder = new AllOrder();
                    allOrder.setThirdOrderId(jdOrder.getId());
                    allOrder.setType(0);
                    allOrder.setTotalMoney(jdOrder.getAmount());
                    allOrder.setCreateUser("sys");
                    allOrder.setCreateTime(new Date());
                    allOrder.setUpdateUser("sys");
                    allOrder.setUpdateTime(new Date());
                    orderService.processJdOrder(allOrder);
                }
            }
        } else {
            List<TmallOrder> tmallOrders = data.stream().map(d -> (TmallOrder) d).collect(Collectors.toList());
            for (TmallOrder tmallOrder : tmallOrders) {
                AllOrder allOrder = new AllOrder();
                allOrder.setThirdOrderId(tmallOrder.getId());
                allOrder.setType(0);
                allOrder.setTotalMoney(tmallOrder.getMoney());
                allOrder.setCreateUser("sys");
                allOrder.setCreateTime(new Date());
                allOrder.setUpdateUser("sys");
                allOrder.setUpdateTime(new Date());
                orderService.processTmallOrder(allOrder);
            }
        }
    }
}
