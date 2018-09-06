package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29 0029.
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Banner> queryAll() {
        List<Banner> banners = bannerDao.queryAll();
        return banners;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Banner> queryAll(Integer page, Integer rows) {
        int result = (page - 1) * rows;
        List<Banner> banners = bannerDao.queryAll1(result, rows);
        return banners;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Integer queryCount() {
        Integer integer = bannerDao.queryCount();
        return integer;
    }

    @Override
    public void insertBanner(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void delete(Integer id) {
        bannerDao.delete(id);
    }

    @Override
    public void save(Integer id, Integer status) {
        bannerDao.save(id, status);
    }

    @Override
    public List<Banner> queryStatus() {
        List<Banner> banners = bannerDao.queryByStatus(1);
        return banners;
    }

}
