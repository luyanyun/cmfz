package com.baizhi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/4 0004.
 */
public class UserDTO implements Serializable {
    private String name;
    private Integer value;

    public UserDTO() {
    }

    public UserDTO(String name, Integer value) {

        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
