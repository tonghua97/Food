package com.example.administrator.ui;

/**
 * Created by Administrator on 2016/12/13.
 */
public class Urls {
    //    public static String mIp = "www.nabyss.cn/foodfun";
    public static String mIp = "10.7.88.38";
//    public static String mIp = "192.168.1.107";
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
    //获取食趣
    public static String urlPickfun = "http://" + mIp + "/http/getFunTitle";
    //获取排行榜
    public static String urlRanklist = "http://" + mIp + "/http/getRanklist";
    //登录URL
    public static String urlIsLogin = "http://" + mIp + "/http/isLogin";
    //食谱收藏
    public static String urlRecipesCollect = "http://" + mIp + "/http/recipesCollect";
    //取消收藏
    public static String urlRecipesCollectDelete = "http://" + mIp + "/http/recipesCollectDelete";
    //获得用户昵称
    public static String urlUserName = "http://" + mIp + "/http/getUserName";
    //获得用户信息
    public static String urlUser = "http://" + mIp + "/http/getUser";
    //食材查询
    public static String urlSearchFood = "http://" + mIp + "/http/searchByRecipesMfood";
    //拾趣收藏
    public static String urlFunCollect = "http://" + mIp + "/http/funCollect";
    //取消拾趣收藏
    public static String urlFunCollectDelete = "http://" + mIp + "/http/funCollectDelete";
    //根据拾趣id获得拾趣集合的URL
    public static String urlFun = "http://" + mIp + "/http/getFunById";
    //获得食谱id
    public static String urlRecipesId = "http://" + mIp + "/http/getRecipesIdByName";
    //修改用户名
    public static String urlSetUserName = "http://" + mIp + "/http/setUserName";
    //修改性别
    public static String urlSetUserSex = "http://" + mIp + "/http/setUserSex";
    //修改食谱收藏
    public static String urlSetRecipesCollect = "http://" + mIp + "/http/setRecipesCollect";
    //修改食趣收藏
    public static String urlSetFunCollect = "http://" + mIp + "/http/setFunCollect";
}