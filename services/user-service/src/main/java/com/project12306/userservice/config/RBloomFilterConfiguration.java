package com.project12306.userservice.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置布隆过滤器
 */
@Configuration
public class RBloomFilterConfiguration {

    private String name = "user_register_cache_penetration_bloom_filter";

    private Long expectedInsertions = 64L;

    private Double falseProbability = 0.03D;

    @Bean
    public RBloomFilter<String> UserRegisterCachePenetrationBloomFilter(RedissonClient redissonClient){
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter(name);
        cachePenetrationBloomFilter.tryInit(expectedInsertions, falseProbability);
        return cachePenetrationBloomFilter;
    }
}
