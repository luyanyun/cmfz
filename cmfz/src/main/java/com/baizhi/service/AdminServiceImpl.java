package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28 0028.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public void insertAdmin(Admin admin) {
        adminDao.insertAdmin(admin);
    }

    @Override
    public List<Admin> queryAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Admin query(String userName, String password) {
        Admin query = adminDao.query(userName);
        if (query.getPassword().equals(password)) {
            return query;
        } else {
            throw new RuntimeException("密码有误");
        }
    }
}
