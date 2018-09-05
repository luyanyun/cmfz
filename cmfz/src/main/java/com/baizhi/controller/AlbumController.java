package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Album> query() {
        List<Album> albums = albumService.queryAll();
        return albums;
    }

    @RequestMapping("/addAlbum")
    public void addAlbum(Album album, @DateTimeFormat(pattern = "yyyy-MM-dd") Date publicDate1, MultipartFile file1, HttpServletRequest request) {
        albumService.insert(album, publicDate1, file1, request);
    }
}
