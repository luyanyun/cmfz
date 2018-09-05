package com.baizhi.controller;

import com.baizhi.entity.SecurityCode;
import com.baizhi.entity.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/28 0028.
 */
@Controller
@RequestMapping("/code")
public class CodeController {
    @RequestMapping("/code")
    public void queryCode(HttpSession session, HttpServletResponse response) {
        String code = SecurityCode.getSecurityCode();
        session.setAttribute("code", code);
        BufferedImage image = SecurityImage.createImage(code);
        ServletOutputStream out = null;
        //获取输出流
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
