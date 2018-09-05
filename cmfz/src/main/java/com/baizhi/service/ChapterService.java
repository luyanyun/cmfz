package com.baizhi.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/9/2 0002.
 */
public interface ChapterService {
    void insert(MultipartFile[] files, Integer albumID, HttpServletRequest request);
}
