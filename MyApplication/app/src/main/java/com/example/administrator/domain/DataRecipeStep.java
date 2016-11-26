package com.example.administrator.domain;

/**
 * Created by wcyhp on 2016/11/24.
 */

public class DataRecipeStep {
    private int id;
    private String No;   //序号
    private String Step;  //步骤

    public DataRecipeStep(int id, String no, String step) {
        this.id = id;
        No = no;
        Step = step;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getStep() {
        return Step;
    }

    public void setStep(String step) {
        Step = step;
    }
}
