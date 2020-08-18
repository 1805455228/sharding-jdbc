package com.example.shardingjdbc.dao;

import com.example.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * @author zhanglei
 * Created by zhanglei on 2020/8/18
 */
@Repository
@MapperScan
public interface UserMapper {

    /**
     * 添加，只需向逻辑表中添加即可，
     */
    @Insert({
            " INSERT INTO tab_user (id,name,age,create_time) ",
            " VALUES ( ",
            "#{id,jdbcType=INTEGER}, ",
            "#{name,jdbcType=VARCHAR}, ",
            "#{age,jdbcType=INTEGER}, ",
            "#{createTime,jdbcType=VARCHAR})"
    })
    int addUser(@Param("id") Integer id,
                @Param("name") String name,
                @Param("age") Integer age,
                @Param("createTime") String create_time);

    @Select({
            "select * from tab_user where id = #{id, jdbcType=INTEGER}"
    })
    User getUser(@Param("id") Integer id);
}
