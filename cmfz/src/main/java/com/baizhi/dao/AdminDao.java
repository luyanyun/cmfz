package com.baizhi.dao;

import com.baizhi.entity.Admin;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28 0028.
 */
public interface AdminDao {
    //增删查
    public void insertAdmin(Admin admin);

    public List<Admin> queryAll();

    public Admin query(String userName);

    public void delete(Integer id);
}
