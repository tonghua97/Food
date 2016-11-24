package com.example.administrator.domain;

/**
 * Created by 刘博园 on 2016/11/24.
 */

public class DataEffect {
    private int id;
    private int url;
    private String mEffect_Name;

    public DataEffect(int id, int url, String mEffect_Name) {
        this.id = id;
        this.url = url;
        this.mEffect_Name = mEffect_Name;
    }

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

    public String getmEffect_Name() {
        return mEffect_Name;
    }

    public void setmEffect_Name(String mEffect_Name) {
        this.mEffect_Name = mEffect_Name;
    }
}
