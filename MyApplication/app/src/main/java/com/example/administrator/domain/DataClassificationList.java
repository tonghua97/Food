package com.example.administrator.domain;

/**
 * 分类列表类
 * Created by lijing on 2016/11/24.
 */

public class DataClassificationList {
    private long Id;      //分类列表页id
    private String recipesid;    //食谱id
    private String Image;    //分类列表图片
    private String Name;  //分类列表名称
    private String Time;     //分类列表用时
    private int Number;   //分类列表收藏人数
    private String Intro;   //分类列表简介

    public DataClassificationList(long id, String recipesid, String image, String name, String time, int number, String intro) {
        Id = id;
        this.recipesid = recipesid;
        Image = image;
        Name = name;
        Time = time;
        Number = number;
        Intro = intro;
    }

    public DataClassificationList() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getRecipesid() {
        return recipesid;
    }

    public void setRecipesid(String recipesid) {
        this.recipesid = recipesid;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }
}