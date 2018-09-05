package com.baizhi.service;

import com.baizhi.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */
public interface AlbumService {
    List<Album> queryAll();

    void insert(Album album, Date date, MultipartFile multipartFile, HttpServletRequest request);
}
