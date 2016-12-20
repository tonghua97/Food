package com.example.administrator.domain;

/**
 * Created by 刘博园 on 2016/11/24.
 */

public class DataCuisine {
    private int id;
    private int url;
    private String mCuisine_Name;

    public DataCuisine(int id, int url, String mCuisine_Name) {
        this.id = id;
        this.url = url;
        this.mCuisine_Name = mCuisine_Name;
    }

//    public DataCuisine(int id, int url) {
//        this.id = id;
//        this.url = url;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getmCuisine_Name() {
        return mCuisine_Name;
    }

    public void setmCuisine_Name(String mCuisine_Name) {
        this.mCuisine_Name = mCuisine_Name;
    }
}
