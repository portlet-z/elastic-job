package com.bytebuf.springbootelasticjob.job;

import com.bytebuf.autoconfig.ElasticDataFlowJob;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 张新征
 * @date: 2019/9/15 10:40 下午
 */
@Slf4j
@ElasticDataFlowJob(jobName = "myDataFlowJob", cron = "0/10 * * * * ?", shardingTotalCount = 2, overwrite = true, streamingProcess = true)
public class MyDataFlowJob implements DataflowJob<Integer> {

    private List<Integer> list = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {
        List<Integer> rtnList = new ArrayList<>();
        for (Integer index : list) {
            if (index % shardingContext.getShardingTotalCount() == shardingContext.getShardingItem()) {
                rtnList.add(index);
                break;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("我是分片项：" + shardingContext.getShardingItem() + ",我抓取的数据是：" + rtnList);
        return rtnList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> data) {
        list.removeAll(data);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("我是分片项：" + shardingContext.getShardingItem() + ",我移除了数据：" + data);
    }
}
