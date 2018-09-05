package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29 0029.
 */
public interface BannerDao {

    public List<Banner> queryAll();

    public void insert(Banner banner);

    public Integer queryCount();

    public List<Banner> queryAll1(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);

    void delete(Integer id);

    void save(@Param(value = "id") Integer id, @Param(value = "status") Integer status);
}
