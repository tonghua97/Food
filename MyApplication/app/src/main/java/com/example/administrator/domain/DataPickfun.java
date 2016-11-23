package com.example.administrator.domain;

/**
 * Created by 13173 on 2016/11/22.
 */

public class DataPickfun {
    private int id;
    private int url;
    private String mPickfun_Name;

    public DataPickfun(int id, int url, String mPickfun_Name) {
        this.id = id;
        this.url = url;
        this.mPickfun_Name = mPickfun_Name;
    }

    public String getmPickfun_Name() {
        return mPickfun_Name;
    }

    public void setmPickfun_Name(String mPickfun_Name) {
        this.mPickfun_Name = mPickfun_Name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
