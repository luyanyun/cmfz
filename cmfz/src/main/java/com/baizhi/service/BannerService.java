package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29 0029.
 */
public interface BannerService {
    public List<Banner> queryAll();

    public List<Banner> queryAll(Integer page, Integer rows);

    public Integer queryCount();

    void insertBanner(Banner banner);

    void delete(Integer id);

    void save(Integer id, Integer status);
}
