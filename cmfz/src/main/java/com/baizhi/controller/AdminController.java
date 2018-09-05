package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/8/28 0028.
 */
@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/register")
    public String register(Admin admin) {
        adminService.insertAdmin(admin);
        return "index";
    }

    @RequestMapping("/login")
    public String login(String userName, String password, String enCode, HttpSession session) {
        String code = (String) session.getAttribute("code");
        if (code.equalsIgnoreCase(enCode)) {
            try {
                Admin query = adminService.query(userName, password);
                session.setAttribute("admin", query);
                return "redirect:/main/main.jsp";
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("erro1", "用户名或密码有误");
                return "login";
            }
        } else {
            session.setAttribute("erro", "验证码有误");
            return "login";
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public Admin queryOne(String name, String password) {
        Admin query = adminService.query(name, password);
        return query;
    }
}
