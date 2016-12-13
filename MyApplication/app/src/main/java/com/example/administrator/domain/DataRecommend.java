package com.example.administrator.domain;

/**
 * 推荐表类
 * Created by 王晨阳 on 2016/11/22.
 */

public class DataRecommend {
    private long Id;    //推荐表id
    private String RecipesId;   //食谱id
    private String Image;   //食谱图片URL
    private String Name;   //食谱名称
    private String Introduction;   //食谱简介

    /**
     * 带参构造方法
     * @param id
     * @param image
     * @param introduction
     * @param name
     * @param recipesId
     */
    public DataRecommend(long id, String recipesId, String image, String name, String introduction) {
        Id = id;
        RecipesId = recipesId;
        Image = image;
        Name = name;
        Introduction = introduction;
    }

    public DataRecommend() {
    }

    /**
     * getter,setter 方法
     */
    public long getId() {
        return Id;
    }

    public void setId(Long id) {
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

    public String getRecipesId() {
        return RecipesId;
    }

    public void setRecipesId(String recipesId) {
        RecipesId = recipesId;
    }
}
