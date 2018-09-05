package com.baizhi.service;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29 0029.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Menu> queryAll() {
        List<Menu> menus = menuDao.queryAll();
        return menus;
    }
}
