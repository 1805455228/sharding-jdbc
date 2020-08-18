package com.example.shardingjdbc.service;

import com.example.shardingjdbc.dao.UserMapper;
import com.example.shardingjdbc.entity.User;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanglei
 * Created by zhanglei on 2020/8/18
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int addUser(User user) {
        HintManager manager = HintManager.getInstance();
        manager.addDatabaseShardingValue("tab_user", user.getCreateTime());
        manager.addTableShardingValue("tab_user", user.getCreateTime());
        int count = userMapper.addUser(
                user.getId(), user.getName(), user.getAge(), user.getCreateTime());
        manager.close();
        return count;
    }

    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }
}
