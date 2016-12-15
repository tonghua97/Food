package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterRecipeStep;
import com.example.administrator.domain.DataRecipe;
import com.example.administrator.domain.DataRecipeStep;
import com.example.administrator.ui.Urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wcyhp on 2016/11/24.
 */

public class RecipeShowActivity extends Activity {
    private TextView mTitle;  //标题
    private ImageView mBack;  //返回按钮
    private ImageView mImage;   //食谱图片URL
    private TextView mName;   //食谱名称
    private TextView mIntroduction;   //食谱简介
    private TextView mLevel;  //难易程度
    private TextView mTaste;  //口味
    private TextView mTime;   //烹饪时长
    private TextView mMajor;  //主料
    private TextView mMinor;  //辅料
    private ImageView mLike;  //收藏按钮
    private TextView mCollect;   //收藏数量
    private TextView mEffect;  //功效
    private ListView mStep;  //步骤
    private ScrollView mSv; //滑动

    private boolean mIscollect = false; //是否收藏
    private List<DataRecipeStep> mStepData = new ArrayList<DataRecipeStep>();
    private AdapterRecipeStep stepAdapter;
    private DataRecipe mRecipe = new DataRecipe();
    private String title;
    private String mRecipesById;
    private String TasteId;
    private String RecipeStep;
    public ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeshow);

        Intent intent = getIntent();
        title = intent.getStringExtra("NAME");
        mRecipesById = intent.getStringExtra("Id");
        //获取控件
        getView();
        //设置ScrollView滚动至最顶端
        mSv.smoothScrollTo(0, 0);
        //获取数据
        getHttpData();
        //getData();
        //stepString2ArrayList(RecipeStep);
        //绑定控件
        setListener();
        //添加数据
        //setViews();
    }

    public void getView(){
        mTitle = (TextView)findViewById(R.id.Tv_recipeshow_title);
        mBack = (ImageView)findViewById(R.id.Iv_recipeshow_back);
        mImage = (ImageView)findViewById(R.id.Iv_recipeshow_image);
        mName = (TextView)findViewById(R.id.Tv_recipeshow_name);
        mIntroduction = (TextView)findViewById(R.id.Tv_recipeshow_introduction);
        mLevel = (TextView)findViewById(R.id.Tv_recipeshow_level);
        mTaste = (TextView)findViewById(R.id.Tv_recipeshow_taste);
        mTime = (TextView)findViewById(R.id.Tv_recipeshow_time);
        mMajor = (TextView)findViewById(R.id.Tv_recipeshow_major);
        mMinor = (TextView)findViewById(R.id.Tv_recipeshow_minor);
        mLike = (ImageView)findViewById(R.id.Iv_recipeshow_like);
        mCollect = (TextView)findViewById(R.id.Tv_recipeshow_like);
        mEffect = (TextView)findViewById(R.id.Tv_recipeshow_effect);
        mStep = (ListView)findViewById(R.id.Lv_recipeshow_step);
        mSv = (ScrollView)findViewById(R.id.SV_recipeshow);
    }

    private void setListener() {
        mBack.setOnClickListener(myListener);
        mLike.setOnClickListener(myListener);

    }
    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Iv_recipeshow_back:
                    finish();
                    break;
                case R.id.Iv_recipeshow_like:
                    if(!mRecipe.iscollect()){
                        mLike.setImageResource(R.drawable.icon_like_down);
                        mRecipe.setCollect(mRecipe.getCollect() + 1);
                        mCollect.setText(mRecipe.getCollect()+"人收藏");
                        mRecipe.setIscollect(true);
                    }else {
                        mLike.setImageResource(R.drawable.icon_like_normal);
                        mRecipe.setCollect(mRecipe.getCollect() -1);
                        mCollect.setText(mRecipe.getCollect()+"人收藏");
                        mRecipe.setIscollect(false);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void getData(){
        //mRecipe.setIscollect(mIscollect);
    }

    private void setView(){
        mTitle.setText(mRecipe.getName());

        String Url = mRecipe.getImage();
        //截取url中ip地址
        String string = Url.substring(7, Url.indexOf("/", 7));
        //替换ip
        Url = Url.replaceAll(string, Urls.mIp);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        imageLoader.displayImage(Url,mImage,options);

        mName.setText(mRecipe.getName());
        mIntroduction.setText(mRecipe.getIntroduction());
        mLevel.setText(mRecipe.getLevel());
        mTaste.setText(mRecipe.getTaste());
        mTime.setText(mRecipe.getTime());
        mMajor.setText(mRecipe.getMajor());
        mMinor.setText(mRecipe.getMinor());
        if(mRecipe.iscollect()) {
            mLike.setImageResource(R.drawable.icon_like_down);
        }
        mCollect.setText(mRecipe.getCollect()+"人收藏");
        mEffect.setText(mRecipe.getEffect());

        stepAdapter = new AdapterRecipeStep(this,mStepData);
        mStep.setAdapter(stepAdapter);
    }

    public void getHttpData() {
        //1、创建网络访问的类的对象
        AsyncHttpClient client = new AsyncHttpClient();
        String recipesUrl = Urls.urlRecipesById + "?recipesId=" + mRecipesById;
        //2、发送请求
        client.get(getApplicationContext(), recipesUrl, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject data = response.getJSONObject(0);
                    mRecipe.setName(data.getString("recipesName"));
                    mRecipe.setImage(data.getString("recipesImage"));
                    mRecipe.setIntroduction(data.getString("recipesIntro"));
                    mRecipe.setLevel(data.getString("recipesLevel"));
                    mRecipe.setTaste(data.getString("classifyName"));
                    mRecipe.setTime(data.getString("recipesTime"));
                    mRecipe.setMajor(data.getString("recipesMfood"));
                    mRecipe.setMinor(data.getString("recipesFood"));
                    mRecipe.setCollect(Integer.parseInt(data.getString("recipesCollect")));
                    mRecipe.setEffect(data.getString("recipesEffect"));
                    RecipeStep = data.getString("recipesStep");
                    stepString2ArrayList(RecipeStep);
                    setView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


//        //创建网络访问的类的对象
//        AsyncHttpClient Client = new AsyncHttpClient();
//        String recipesUrl = Urls.urlRecipesById + "?recipesId=" + mRecipesById;
//        Log.e("============",recipesUrl);
//        //发送请求获取食谱详情
//        Client.get(getApplicationContext(), recipesUrl, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                try {
//                    JSONObject data = response.getJSONObject(0);
//                    String name = data.getString("recipesName");
//                    String image = data.getString("recipesImage");
//                    String introduction = data.getString("recipesIntro");
//                    String level = data.getString("recipesLevel");
//                    String taste = data.getString("classifyName");
//                    String time = data.getString("recipesTime");
//                    String major = data.getString("recipesMfood");
//                    String minor = data.getString("recipesFood");
//                    int collect = Integer.parseInt(data.getString("recipesCollect"));
//                    String effect = data.getString("recipesEffect");
//                    mRecipe = new DataRecipe(Integer.parseInt(mRecipesById), image, name, introduction,
//                            level, taste, time, major, minor, collect, false, effect);
//                    RecipeStep = data.getString("recipesStep");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.e("========", "begin");
//                }
//
//            }
//        });
    }
    public void stepString2ArrayList(String RecipeStep){
        if (RecipeStep == null){
            return;
        }
        String [] stringArr= RecipeStep.split("\r\n");
        for(int i = 0; i < stringArr.length;i++){
            String No = stringArr[i].substring(0,stringArr[i].indexOf("."));
            //个位数前面加"0"
            if(No.length() < 2 ){
                No = "0"+No;
            }
            String step = stringArr[i].substring(stringArr[i].indexOf(".")+1);
            mStepData.add(new DataRecipeStep(i, No +"、", step));
        }
    }
}
