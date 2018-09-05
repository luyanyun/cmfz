package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/8/29 0029.
 */
public class Banner implements Serializable {
    private Integer id;
    private String title;
    private String imgPath;
    private String description;
    private Integer status;
    private Date createDate;

    public Banner(Integer id, String title, String imgPath, String description, Integer status, Date createDate) {
        this.id = id;
        this.title = title;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
    }

    public Banner() {

    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
