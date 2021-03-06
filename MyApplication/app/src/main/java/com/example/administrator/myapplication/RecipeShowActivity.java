package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
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

    private List<DataRecipeStep> mStepData = new ArrayList<DataRecipeStep>();
    private AdapterRecipeStep stepAdapter;
    private DataRecipe mRecipe = new DataRecipe();
    private String title;
    private String mRecipesById;
    private String TasteId;
    private String RecipeStep;
    public ImageLoader imageLoader = ImageLoader.getInstance();
    private String str;
    private String userId;
    private ProgressDialog dialog;

    private Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /**
             * @msg.what   1表示来自于添加过程； 2表示来自于删除过程
             * @msg.arg1   1表示成功； 2表示失败
             */
            if (msg.what == 1){
                if(msg.arg1 ==1){
                    Toast.makeText(getApplicationContext(),"收藏成功",Toast.LENGTH_SHORT).show();
                    mLike.setImageResource(R.drawable.icon_like_down);
                    mRecipe.setIscollect(true);
                }else {
                    Toast.makeText(getApplicationContext(),"该食谱已收藏",Toast.LENGTH_SHORT).show();
                }
            }else if(msg.what ==2){
                if(msg.arg1 ==1){
                    Toast.makeText(getApplicationContext(),"取消成功",Toast.LENGTH_SHORT).show();
                    mLike.setImageResource(R.drawable.icon_like_normal);
                    mRecipe.setIscollect(false);
                }
            }
            mCollect.setText(mRecipe.getCollect()+"人收藏");
        }
    };

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
        //获取用户名
        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");
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
        dialog = new ProgressDialog(RecipeShowActivity.this);
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
                        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
                        userId = spf.getString("userId","");
                        if (userId == ""){
                            Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RecipeShowActivity.this,LoginVerifyActivity.class);
                            startActivity(intent);
                        }else {
                            Thread thread = new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    String result = setCollect("add");
                                    Message m = new Message();
                                    m.what = 1;
                                    if(result.equals("success")) {
                                        m.arg1 = 1;
                                    }else {
                                        m.arg1 = 2;
                                    }
                                    h.sendMessage(m);
                                }
                            };
                            thread.start();
                        }

                    }else {
                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                String result = setCollect("delete");
                                Message m = new Message();
                                m.what = 2;
                                if(result.equals("success")) {
                                    m.arg1 = 1;
                                }else {
                                    m.arg1 = 2;
                                }
                                h.sendMessage(m);
                            }
                        };
                        thread.start();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void setView(){
        mTitle.setText(mRecipe.getName());

        String Url = mRecipe.getImage();

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
        }else {
            mLike.setImageResource(R.drawable.icon_like_normal);
        }
        mCollect.setText(mRecipe.getCollect()+"人收藏");
        mEffect.setText(mRecipe.getEffect());

        stepAdapter = new AdapterRecipeStep(this,mStepData);
        mStep.setAdapter(stepAdapter);
    }

    public void getHttpData() {
        //1、创建网络访问的类的对象
        AsyncHttpClient client = new AsyncHttpClient();
        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");
        String recipesUrl = Urls.urlRecipesById + "?recipesId=" + mRecipesById +"&userId=" + userId;
        //2、发送请求
        client.get(getApplicationContext(), recipesUrl, new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                super.onStart();
                dialog.setTitle("提示信息");
                dialog.setMessage("数据加载中......");
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                }.start();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String isCollect = response.getString("IsCollect");
                    JSONArray recipes = response.getJSONArray("Recipes");
                    JSONObject data = recipes.getJSONObject(0);
                    mRecipe.setName(data.getString("recipesName"));
                    mRecipe.setImage(data.getString("recipesImage"));
                    mRecipe.setIntroduction(data.getString("recipesIntro"));
                    mRecipe.setLevel(data.getString("recipesLevel"));
                    mRecipe.setTaste(data.getString("classifyName"));
                    mRecipe.setTime(data.getString("recipesTime"));
                    mRecipe.setMajor(data.getString("recipesMfood"));
                    mRecipe.setMinor(data.getString("recipesFood"));
                    if(isCollect.equals("true")){
                        mRecipe.setIscollect(true);
                    }else {
                        mRecipe.setIscollect(false);
                    }
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
    }
    public void stepString2ArrayList(String RecipeStep){
        mStepData.clear();
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

    public String setCollect(String Operate){
        String result = "";
        str = "";
        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");
        try {
            String recipesUrl = Urls.urlSetRecipesCollect;
            URI u = new URI(recipesUrl);

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("recipesId",mRecipesById);
            NameValuePair pair2 = new BasicNameValuePair("userId",userId);
            NameValuePair pair3 = new BasicNameValuePair("operate",Operate);
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);
            pairs.add(pair2);
            pairs.add(pair3);

            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string="";

                while ((string = buffer.readLine()) != null) {
                    str += string;
                }
            }
            JSONObject json = new JSONObject(str);
            result = json.getString("msg");
            Log.e("setCollect: ", result);
            int collect = json.getInt("num");
            if(result.equals("success")){
                mRecipe.setCollect(collect);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHttpData();
    }
}
