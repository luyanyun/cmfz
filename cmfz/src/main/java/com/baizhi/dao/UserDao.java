package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/9/3 0003.
 */
public interface UserDao {
    void insert(@Param(value = "list") List<User> list);

    List<User> queryAll();

    List<User> queryAll1(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);

    Integer cout();

    Integer queryDate(Integer li);

    List<UserDTO> queryCentry(String name);
}
