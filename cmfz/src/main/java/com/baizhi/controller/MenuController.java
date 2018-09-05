package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29 0029.
 */
@Controller
@RequestMapping("/query")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/queryMenu")
    @ResponseBody
    public List<Menu> queryAll() {
        List<Menu> menus = menuService.queryAll();
        return menus;
    }
}
