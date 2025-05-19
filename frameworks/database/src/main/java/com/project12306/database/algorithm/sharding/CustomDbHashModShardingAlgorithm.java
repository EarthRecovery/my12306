package com.project12306.database.algorithm.sharding;

import org.apache.shardingsphere.sharding.algorithm.sharding.ShardingAutoTableAlgorithmUtil;
import org.apache.shardingsphere.sharding.algorithm.sharding.mod.HashModShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

public final class CustomDbHashModShardingAlgorithm implements StandardShardingAlgorithm<Comparable<?>> {

    private static final String SHARDING_COUNT_KEY = "sharding-count";
    private static final String TABLE_SHARDING_COUNT_KEY = "table-sharding-count";

    private int shardingCount;
    private int tableShardingCount;

    @Override
    public void init(final Properties properties) {
        shardingCount = Integer.parseInt(properties.getProperty(SHARDING_COUNT_KEY));
        tableShardingCount = Integer.parseInt(properties.getProperty(TABLE_SHARDING_COUNT_KEY));
    }

    /**
     * 核心分片算法
     * 根据哈希以后的值进行分片，0-15查表1，16-31查表2
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Comparable<?>> preciseShardingValue) {
        long hashCode = hash(preciseShardingValue.getValue());
        String suffix = String.valueOf(hashCode % shardingCount / tableShardingCount);
        return ShardingAutoTableAlgorithmUtil
                .findMatchedTargetName(collection, suffix,preciseShardingValue.getDataNodeInfo()).orElse(null);
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Comparable<?>> rangeShardingValue) {
        return collection;
    }

    private long hash(final Object shardingValue){
        return Math.abs((long) shardingValue.hashCode());
    }

    @Override
    public String getType() {
        return "CLASS_BASED";
    }
}
