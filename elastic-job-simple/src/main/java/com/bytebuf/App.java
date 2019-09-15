package com.bytebuf;

import com.bytebuf.job.MyDataFlowJob;
import com.bytebuf.job.MySimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println("Hello World");
        new JobScheduler(zkCenter(), configurationScript()).init();
    }

    /**
     * 注册中心
     * @return
     */
    public static CoordinatorRegistryCenter zkCenter() {
        ZookeeperConfiguration zc = new ZookeeperConfiguration("localhost:2181", "java-simple-job");
        CoordinatorRegistryCenter crc = new ZookeeperRegistryCenter(zc);
        //注册中心初始化
        crc.init();
        return crc;
    }

    /**
     * job 配置
     * @return
     */
    public static LiteJobConfiguration configuration() {
        //job 核心配置
        JobCoreConfiguration jcc = JobCoreConfiguration
                .newBuilder("mySimpleJob", "0/5 * * * * ?", 2)
                .build();
        //job 类型配置
        JobTypeConfiguration jtc = new SimpleJobConfiguration(jcc, MySimpleJob.class.getCanonicalName());
        //job 根的配置(LiteJobConfiguration)
        LiteJobConfiguration ljc = LiteJobConfiguration
                .newBuilder(jtc)
                .overwrite(true)
                .build();
        return ljc;
    }


    /**
     * job 配置
     * @return
     */
    public static LiteJobConfiguration configurationDataflow() {
        //job 核心配置
        JobCoreConfiguration jcc = JobCoreConfiguration
                .newBuilder("myDataflowJob", "0/10 * * * * ?", 2)
                .build();
        //job 类型配置
        JobTypeConfiguration jtc = new DataflowJobConfiguration(jcc, MyDataFlowJob.class.getCanonicalName(), true);
        //job 根的配置(LiteJobConfiguration)
        LiteJobConfiguration ljc = LiteJobConfiguration
                .newBuilder(jtc)
                .overwrite(true)
                .build();
        return ljc;
    }

    /**
     * job 配置
     * @return
     */
    public static LiteJobConfiguration configurationScript() {
        //job 核心配置
        JobCoreConfiguration jcc = JobCoreConfiguration
                .newBuilder("myScriptJob", "0/1 * * * * ?", 2)
                .build();
        //job 类型配置
        JobTypeConfiguration jtc = new ScriptJobConfiguration(jcc, "/Users/zhangxinzheng/Developer/elastic-job/elastic-job-simple/test.sh");
        //job 根的配置(LiteJobConfiguration)
        LiteJobConfiguration ljc = LiteJobConfiguration
                .newBuilder(jtc)
                .overwrite(true)
                .build();
        return ljc;
    }
}
