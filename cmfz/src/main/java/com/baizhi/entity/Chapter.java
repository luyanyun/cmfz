package com.baizhi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/30 0030.
 */
public class Chapter implements Serializable {
    private String id;
    private String name;
    private String duration;//时长
    private String size;
    private String audioPath;//饮品路径
    private Integer album_id;

    public Chapter() {
    }

    public Chapter(String id, String name, String duration, String size, String audioPath, Integer album_id) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.audioPath = audioPath;
        this.album_id = album_id;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                ", audioPath='" + audioPath + '\'' +
                ", album_id=" + album_id +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }
}
