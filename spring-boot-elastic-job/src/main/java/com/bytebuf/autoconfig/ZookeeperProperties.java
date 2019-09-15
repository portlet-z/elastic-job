package com.bytebuf.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: 张新征
 * @date: 2019-09-15 20:34
 */
@ConfigurationProperties(prefix = "elasticjob.zookeeper")
@Data
public class ZookeeperProperties {

    private String serverList;
    private String namespace;
}
