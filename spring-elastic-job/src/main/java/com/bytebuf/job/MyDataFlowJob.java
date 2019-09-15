package com.bytebuf.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 张新征
 * @date: 2019/9/15 10:28 下午
 */
@Slf4j
public class MyDataFlowJob implements DataflowJob<Integer> {
    
    private List<Integer> list = new ArrayList<>();
    
    {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }
    
    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {
        //数字 % 分片总数 == 当前分片项
        List<Integer> rtnList = new ArrayList<>();
        for (Integer index : list) {
            if (index % shardingContext.getShardingTotalCount() == shardingContext.getShardingItem()) {
                rtnList.add(index);
                break;
            }
        }
        System.out.println("我是分片项：" + shardingContext.getShardingItem() + ",我获取的数据是：" + rtnList);
        log.info("我是分片项：" + shardingContext.getShardingItem() + ",我获取的数据是：" + rtnList);
        return rtnList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> data) {
        list.removeAll(data);
        System.out.println("我是分片项：" + shardingContext.getShardingItem() + ",我移除的数据是：" + data);
        log.info("我是分片项：" + shardingContext.getShardingItem() + ",我移除的数据是：" + data);
    }
}
