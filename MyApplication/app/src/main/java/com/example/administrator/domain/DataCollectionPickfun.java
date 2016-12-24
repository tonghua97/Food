package com.example.administrator.domain;

/**
 * 收藏类
 * Created by lijing on 2016/11/23.
 */

public class DataCollectionPickfun {
    private String Id;    //收藏页id
    private String Image;   //收藏图片
    private String Name;   //收藏名称
    private String Introduction;   //收藏简介

    public DataCollectionPickfun() {
    }

    public DataCollectionPickfun(String id, String image, String introduction, String name) {
        Id = id;
        Image = image;
        Introduction = introduction;
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
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