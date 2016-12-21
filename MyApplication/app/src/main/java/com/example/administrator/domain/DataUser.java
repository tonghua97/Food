package com.example.administrator.domain;

/**
 * Created by Administrator on 2016/12/21.
 */
public class DataUser {
    private String userNum;
    private String userSex;
    private String userImage;
    private String userAccount;
    private String userPost;
    private String userName;

    public DataUser(String userNum, String userSex, String userImage, String userAccount, String userPost, String userName) {
        this.userNum = userNum;
        this.userSex = userSex;
        this.userImage = userImage;
        this.userAccount = userAccount;
        this.userPost = userPost;
        this.userName = userName;
    }

    public DataUser() {
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
