package com.example.administrator.domain;

/**
 * 收藏类
 * Created by lijing on 2016/11/23.
 */

public class DataCollection {
    private long Id;    //收藏页id
    private int Image;   //收藏图片
    private String Name;   //收藏名称
    private String Introduction;   //收藏简介

    public DataCollection(long id, int image, String introduction, String name) {
        Id = id;
        Image = image;
        Introduction = introduction;
        Name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}