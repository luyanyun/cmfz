package com.baizhi.controller;

import com.baizhi.Util.DownloadUtil;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/9/2 0002.
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/add")
    public void add(@RequestParam("file1") MultipartFile[] file1, Integer albumID, HttpServletRequest request) {
        chapterService.insert(file1, albumID, request);
    }

    @RequestMapping("/downLoad")
    public void downLoad(String saveName, String dbFileName, HttpServletRequest request, HttpServletResponse response) {
        DownloadUtil.download(saveName, dbFileName, request, response);
    }
}
