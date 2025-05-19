package com.project12306.userservice.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.redisson.config.Config;
import org.springframework.util.StringUtils;

import java.util.List;

@Configuration
public class RedissonConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        if(redisProperties.getCluster() != null) {
            List<String> nodes = redisProperties.getCluster().getNodes();

            List<String> clusterNodes = nodes.stream()
                    .map(node -> node.startsWith("redis://") ? node : "redis://" + node)
                    .toList();

            ClusterServersConfig clusterServersConfig = config.useClusterServers()
                    .addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));

            if(!StringUtils.isEmpty(redisProperties.getPassword())) {
                clusterServersConfig.setPassword(redisProperties.getPassword());
            }

        }
        config.setLockWatchdogTimeout(15000);
        return Redisson.create(config);
    }
}
