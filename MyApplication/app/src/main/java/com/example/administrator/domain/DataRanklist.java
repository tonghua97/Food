package com.example.administrator.domain;

/**
 * Created by Sophia on 2016/11/24.
 */
public class DataRanklist {
    private long Id;            //食谱id
    private String RecipesId;   //食谱id
    private String Image;       //食谱图片URL Iv_ranklist_recipeimg
    private String Name;        //食谱名称 Tv_ranklist_recipename
    private int num;            //食谱收藏数 Tv_ranklist_collect
    private int rank;           //食谱排行 Tv_rank_id

    public DataRanklist(long id, String recipesId, String image, String name, int num, int rank) {
        Id = id;
        RecipesId = recipesId;
        Image = image;
        Name = name;
        this.num = num;
        this.rank = rank;
    }

    public DataRanklist(){

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getRecipesId() {
        return RecipesId;
    }

    public void setRecipesId(String recipesId) {
        RecipesId = recipesId;
    }
}
