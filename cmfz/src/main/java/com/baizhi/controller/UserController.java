package com.baizhi.controller;

import com.baizhi.Util.DownloadUtil;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/3 0003.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        DownloadUtil.download("用户信息录入模板", "用户信息录入模板.xls", request, response);
    }

    @RequestMapping("/import")
    public void importUser(MultipartFile file1) {
        userService.insert(file1);
    }

    @RequestMapping("/export")
    public void exportUser(HttpServletResponse response) {
        userService.export(response);
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer integer = userService.queryCout();
        List<User> users = userService.queryAll(page, rows);
        map.put("total", integer);
        map.put("rows", users);
        return map;
    }

    @RequestMapping("/freeSelect")
    public void exportFree(HttpServletResponse response, String titls, String params) {
        userService.exportFree(response, titls, params);
    }

    @RequestMapping("/queryCount")
    @ResponseBody
    public List<Integer> queryCount() {
        List<Integer> integers = userService.queryDate();
        return integers;
    }

    @RequestMapping("/queryCity")
    @ResponseBody
    public Map<String, Object> queryCity() {
        Map<String, Object> stringObjectMap = userService.queryCentry();
        return stringObjectMap;
    }
}
