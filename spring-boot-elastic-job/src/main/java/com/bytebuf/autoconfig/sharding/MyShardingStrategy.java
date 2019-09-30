package com.bytebuf.autoconfig.sharding;

import com.dangdang.ddframe.job.lite.api.strategy.JobInstance;
import com.dangdang.ddframe.job.lite.api.strategy.JobShardingStrategy;

import java.util.*;

/**
 * @author: 张新征
 * @date: 2019/9/24 11:27 下午
 */
public class MyShardingStrategy implements JobShardingStrategy {
    @Override
    public Map<JobInstance, List<Integer>> sharding(List<JobInstance> jobInstances, String jobName, int shardingTotalCount) {
        Map<JobInstance, List<Integer>> rtnMap = new HashMap<>(10);
        ArrayDeque<Integer> queue = new ArrayDeque<>(shardingTotalCount);
        for (int i = 0; i < shardingTotalCount; i++) {
            queue.add(i);
        }
        while (queue.size() > 0) {
            for (JobInstance jobInstance : jobInstances) {
                if (queue.size() <= 0) {
                    continue;
                }
                Integer shardingItem = queue.pop();
                List<Integer> integers = rtnMap.get(jobInstance);
                if (integers != null && integers.size() > 0) {
                    integers.add(shardingItem);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(shardingItem);
                    rtnMap.put(jobInstance, list);
                }
            }
        }
        return rtnMap;
    }
}
