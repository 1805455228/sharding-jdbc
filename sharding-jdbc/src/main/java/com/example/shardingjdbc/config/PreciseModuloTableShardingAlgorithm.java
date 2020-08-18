package com.example.shardingjdbc.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author zhanglei
 * Created by zhanglei on 2020/8/18
 */
public class PreciseModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection,
                             PreciseShardingValue<String> prec) {
        //对于库的分片collection存放的是所有的库的列表，这里代表master04091,master04092
        //配置的分片的sharding-column对应的值
        String timeValue = prec.getValue();
        //判断timeValue是否为空
        if (StringUtils.isBlank(timeValue)) {
            throw new UnsupportedOperationException("prec is null");
        }
        // 按年路由，一个库中有5年的数据，在库中我们将每年分成一个表，
        // 对5取余，判断得到的所有表名后缀是否与取余一致，一致就返回
        for (String each : collection) {
            //得到具体年,截取字符串要头不要尾
            String value = StringUtils.substring(timeValue, 0, 4);
            int c = Integer.parseInt(value);
            //循环每个库，看哪个库与当前条件匹配
            if (each.endsWith(Integer.toString(c % 5))) {
                return each;
            }
        }
        return null;
    }


}
