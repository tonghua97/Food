package com.example.administrator.domain;

/**
 * 分类列表类
 * Created by lijing on 2016/11/24.
 */

public class DataClassificationList {
    private long Id;      //分类列表页id
    private int Image;    //分类列表图片
    private String Name;  //分类列表名称
    private int Time;     //分类列表用时
    private int Number;   //分类列表收藏人数
    private String Material;   //分类列表食材

    public DataClassificationList(long id, int image, String material, String name, int number, int time) {
        Id = id;
        Image = image;
        Material = material;
        Name = name;
        Number = number;
        Time = time;
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

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }
}