package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2018/8/28 0028.
 */
@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
