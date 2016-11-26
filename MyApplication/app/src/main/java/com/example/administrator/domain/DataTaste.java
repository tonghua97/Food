package com.example.administrator.domain;

/**
 * Created by 刘博园 on 2016/11/24.
 */

public class DataTaste {
    private int id;
    private int url;
    private String mTaste_Name;

    public DataTaste(int id, int url, String mTaste_Name) {
        this.id = id;
        this.url = url;
        this.mTaste_Name = mTaste_Name;
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

    public String getmTaste_Name() {
        return mTaste_Name;
    }

    public void setmTaste_Name(String mTaste_Name) {
        this.mTaste_Name = mTaste_Name;
    }
}
