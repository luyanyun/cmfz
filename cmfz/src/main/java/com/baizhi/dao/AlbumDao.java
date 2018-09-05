package com.baizhi.dao;

import com.baizhi.entity.Album;

import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */
public interface AlbumDao {
    List<Album> queryAll();

    void insert(Album album);
}
