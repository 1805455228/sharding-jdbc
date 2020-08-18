package com.example.shardingjdbc.config;

import com.google.common.collect.Lists;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.List;

/**
 * @author zhanglei
 * 强制路由只有特殊情况才会使用，必须在调用数据库前使用HintManager将database和table的逻辑表设置，
 * 如果不设置，sharding-jdbc会遍历执行所有路由，一旦设置HintManager，datasource和table都需要设
 * 置对应的路由算法{{@link #doSharding(Collection, HintShardingValue)}}来决定是哪个路由
 * Created by zhanglei on 2020/8/18
 */
public class HintTableShardingAlgorithm implements HintShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hint) {
        System.out.println(collection);
        System.out.println(hint);
        // actualTable中包含sharding-jdbc实际会查询的表
        return Lists.newArrayList("tab_user1");
    }
}
