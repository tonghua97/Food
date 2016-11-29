package com.example.administrator.domain;

/**
 * Created by 姜佳妮 on 2016/11/29.
 */
public class DataMaterial {
    private Long id;        //ID
    private String name;    //食材名称

    public DataMaterial(Long id, String Name) {
        this.id = id;
        name = Name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
