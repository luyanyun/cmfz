package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2018/8/29 0029.
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/banner")
    @ResponseBody
    public List<Banner> queryAll() {
        List<Banner> banners = bannerService.queryAll();
        return banners;
    }

    @RequestMapping("/banner1")
    @ResponseBody
    public Map<String, Object> queryAll1(Integer page, Integer rows) {// 给后台管理系统
        List<Banner> banners = bannerService.queryAll(page, rows);
        Integer integer = bannerService.queryCount();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", integer);
        map.put("rows", banners);
        return map;
    }

    @RequestMapping("/add")
    public void insertBanner(Banner banner, MultipartFile imgPath1, HttpServletRequest request) {
        //1.获得存储文件的全路径
        //2.获得获得唯一文件名包括后缀
        //3.调用MutipartFile的 transferTo方法
        String realPath = request.getServletContext().getRealPath("/");
        String path = realPath + "upload";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        String name = imgPath1.getOriginalFilename();
        String s = UUID.randomUUID().toString();
        String extension = FilenameUtils.getExtension(name);//文件的扩展名
        String newName = s + "." + extension;//获得唯一的一个新名字
        try {
            imgPath1.transferTo(new File(path, newName)); //文件上传需要的路径和唯一的文件名
        } catch (IOException e) {
            e.printStackTrace();
        }
        banner.setImgPath("/" + newName);
        banner.setStatus(1);
        banner.setCreateDate(new Date());
        bannerService.insertBanner(banner);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        bannerService.delete(id);
    }

    @RequestMapping("/save")
    public void save(Integer id, Integer status) {
        bannerService.save(id, status);
    }
}
