package com.baizhi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/28 0028.
 */
public class Admin implements Serializable {
    private Integer id;
    private String userName;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(Integer id, String userName, String password) {

        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Admin() {

    }
}
