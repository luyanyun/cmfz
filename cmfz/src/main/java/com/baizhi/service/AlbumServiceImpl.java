package com.baizhi.service;

import com.baizhi.Util.UploadUtil;
import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Album> queryAll() {
        List<Album> albums = albumDao.queryAll();
        return albums;
    }

    @Override
    public void insert(Album album, Date date, MultipartFile multipartFile, HttpServletRequest request) {
        String newName = UploadUtil.upload(multipartFile, request);
        album.setCorverImg(newName);
        album.setPublicDate(date);
        album.setCreateDate(new Date());
        albumDao.insert(album);
    }
}
