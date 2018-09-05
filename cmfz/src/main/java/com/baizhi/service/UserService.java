package com.baizhi.service;

import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/9/3 0003.
 */
public interface UserService {
    void insert(MultipartFile file);

    void export(HttpServletResponse response);

    List<User> queryAll(Integer page, Integer rows);

    Integer queryCout();

    void exportFree(HttpServletResponse response, String titls, String params);

    List<Integer> queryDate();

    Map<String, Object> queryCentry();
}
