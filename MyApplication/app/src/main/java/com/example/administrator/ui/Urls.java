package com.example.administrator.ui;

/**
 * Created by Administrator on 2016/12/13.
 */
public class Urls {
//    public static String mIp = "www.nabyss.cn/foodfun";
    public static String mIp = "10.7.88.96";
    //按名称搜索URL
    public static String urlSearch = "http://" + mIp + "/http/searchByRecipesName";
    //获取推荐的名称和简介及美食id,图片的URL
    public static String urlCommend = "http://" + mIp + "/http/getCommend";
    //根据recipesId获得美食集合的URL
    public static String urlRecipesById = "http://" + mIp + "/http/getRecipesById";
    //搜索列表
    public static String urlSearchShow = "http://" + mIp + "/http/searchShowByRecipesName";
    //根据分类的名称获取美食的列表的URL
    public static String urlClassifyList = "http://" + mIp + "/http/getClassifyListByName";
    //时间搜索
    public static String urlSearchTime = "http://" + mIp + "/http/searchByRecipesTime";
    //判断账号是否存在的URL
    public static String urlIsUserAccount = "http://" + mIp + "/http/isUserAccount";
    //注册接口的URL
    public static String urlRegister = "http://" + mIp + "/http/register";
    //登录URL
    public static String urlIsLogin = "http://" + mIp + "/http/isLogin";
}
