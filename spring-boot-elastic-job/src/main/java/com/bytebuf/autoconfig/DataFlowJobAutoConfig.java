package com.bytebuf.autoconfig;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: 张新征
 * @date: 2019/9/15 10:04 下午
 */
@Configuration
@ConditionalOnBean(CoordinatorRegistryCenter.class)
@AutoConfigureAfter(ZookeeperAutoConfig.class)
public class DataFlowJobAutoConfig {

    @Autowired
    private CoordinatorRegistryCenter zkCenter;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initSimpleJob() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ElasticDataFlowJob.class);
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object instance = entry.getValue();
            Class<?>[] interfaces = instance.getClass().getInterfaces();
            for (Class<?> superInterface : interfaces) {
                if (superInterface == DataflowJob.class) {
                    ElasticDataFlowJob annotation = instance.getClass().getAnnotation(ElasticDataFlowJob.class);
                    String jobName = annotation.jobName();
                    String cron = annotation.cron();
                    int shardingTotalCount = annotation.shardingTotalCount();
                    boolean overwrite = annotation.overwrite();
                    boolean streamingProcess = annotation.streamingProcess();
                    Class<?> jobStrategy = annotation.jobStrategy();
                    boolean isJobEvent = annotation.isJobEvent();
                    Class<? extends ElasticJobListener>[] listeners = annotation.jobListener();
                    ElasticJobListener[] listenersInstances = null;
                    if (listeners != null && listeners.length > 0) {
                        listenersInstances = new ElasticJobListener[listeners.length];
                        int i = 0;
                        for (Class<? extends ElasticJobListener> listener : listeners) {
                            ElasticJobListener listenerInstance = listener.getDeclaredConstructor().newInstance();
                            listenersInstances[i++] = listenerInstance;
                        }
                    } else {
                        listenersInstances = new ElasticJobListener[0];
                    }
                    JobCoreConfiguration jcc = JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount).build();
                    JobTypeConfiguration jtc = new DataflowJobConfiguration(jcc, instance.getClass().getCanonicalName(), streamingProcess);

                    LiteJobConfiguration ljc = LiteJobConfiguration
                            .newBuilder(jtc)
                            .jobShardingStrategyClass(jobStrategy.getCanonicalName())
                            .overwrite(overwrite)
                            .build();
                    if (isJobEvent) {
                        JobEventConfiguration jec = new JobEventRdbConfiguration(dataSource);
                        new SpringJobScheduler((ElasticJob) instance, zkCenter, ljc, jec, listenersInstances).init();
                    } else {
                        new SpringJobScheduler((ElasticJob) instance,zkCenter, ljc, listenersInstances).init();
                    }
                }
            }
        }
    }
}
