package com.example.administrator.domain;

/**
 * Created by 13173 on 2016/11/22.
 */

public class DataPickfun {
    private int id;
    private String url;
    private String mPickfun_Name;

    public DataPickfun(){

    }
    public DataPickfun(int id, String url, String mPickfun_Name) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
