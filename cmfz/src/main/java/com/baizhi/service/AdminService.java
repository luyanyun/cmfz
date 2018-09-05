package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28 0028.
 */
public interface AdminService {
    public void insertAdmin(Admin admin);

    public List<Admin> queryAll();

    public void delete(Integer id);

    public Admin query(String userName, String password);
}
