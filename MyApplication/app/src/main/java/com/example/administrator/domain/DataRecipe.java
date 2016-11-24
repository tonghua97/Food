package com.example.administrator.domain;

/**
 * Created by wcyhp on 2016/11/24.
 */

public class DataRecipe {
    private long RecipesId;   //食谱id
    private String Image;   //食谱图片URL
    private String Name;   //食谱名称
    private String Introduction;   //食谱简介
    private String Level;  //难易程度
    private String Taste;  //口味
    private String Time;   //烹饪时长
    private String Major;  //主料
    private String Minor;  //辅料
    private int Collect;   //收藏数量
    private boolean Iscollect = false; //是否收藏
    private String Effect;  //功效

    /**
     * 带参构造函数
     * @param recipesId  食谱id
     * @param image  图片URL
     * @param name   名称
     * @param introduction  简介
     * @param level  难易程度
     * @param taste  口味
     * @param time   时长
     * @param major  主料
     * @param minor  辅料
     * @param collect  收藏数量
     * @param iscollect  是否收藏
     * @param effect  功效
     */
    public DataRecipe(long recipesId, String image, String name, String introduction,
                      String level, String taste, String time, String major, String minor,
                      int collect, boolean iscollect, String effect ) {
        Collect = collect;
        Effect = effect;
        Image = image;
        Introduction = introduction;
        Iscollect = iscollect;
        Level = level;
        Major = major;
        Minor = minor;
        Name = name;
        RecipesId = recipesId;
        Taste = taste;
        Time = time;
    }

    public int getCollect() {
        return Collect;
    }

    public void setCollect(int collect) {
        Collect = collect;
    }

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String effect) {
        Effect = effect;
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

    public boolean iscollect() {
        return Iscollect;
    }

    public void setIscollect(boolean iscollect) {
        Iscollect = iscollect;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getMinor() {
        return Minor;
    }

    public void setMinor(String minor) {
        Minor = minor;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getRecipesId() {
        return RecipesId;
    }

    public void setRecipesId(long recipesId) {
        RecipesId = recipesId;
    }

    public String getTaste() {
        return Taste;
    }

    public void setTaste(String taste) {
        Taste = taste;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
