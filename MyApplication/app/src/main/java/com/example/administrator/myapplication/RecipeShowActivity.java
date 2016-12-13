package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterRecipeStep;
import com.example.administrator.domain.DataRecipe;
import com.example.administrator.domain.DataRecipeStep;
import com.example.administrator.ui.Urls;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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

    private boolean mIscollect = false; //是否收藏
    private List<DataRecipeStep> mStepData = new ArrayList<DataRecipeStep>();
    private AdapterRecipeStep stepAdapter;
    private DataRecipe mRecipe;
    private String title;
    private String mRecipesById;
    private String str;

    private Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            
            setParse();
        }
    };

    private void setParse() {
        if (str == ""){
            return;
        }else{
            str = str.substring(str.indexOf("["), str.length());

            try {
                JSONArray jsonArray = new JSONArray(str);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeshow);

        Intent intent = getIntent();
        title = intent.getStringExtra("NAME");
        mRecipesById = intent.getStringExtra("Id");

        //获取控件
        getView();
        //获取数据
//        getData();
        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
                getHttpData();
                
                Message m = new Message();
                h.sendMessage(m);
            }
        };
        t.start();
        
        //绑定控件
        setListener();
        //添加数据
        setView();
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
        mRecipe = new DataRecipe(0L,"URL","茶泡饭","简介简介简介简介简介简介简介简介",
                "简单","咸鲜","10分钟",
                "主料、主料、主料",
                "辅料、辅料、辅料、辅料",
                103,false,
                "功效功效功效功效功效功效功效功效功效功效功效功效功效功效功效功效功");
        mRecipe.setName(title);
        mStepData.add(new DataRecipeStep(0,"1、","第一步"));
        mStepData.add(new DataRecipeStep(1,"2、","第二步"));
        mStepData.add(new DataRecipeStep(2,"3、","第三步"));
        mStepData.add(new DataRecipeStep(3,"4、","第四步"));
        mStepData.add(new DataRecipeStep(4,"5、","第五步"));

    }

    private void setView(){
        mTitle.setText(mRecipe.getName());
        mImage.setImageResource(R.drawable.img_loading);
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
        try {
            URI u = new URI(Urls.urlRecipesById);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("recipesId", mRecipesById);

            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);

            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = null;

                while ((string = buffer.readLine()) != null) {
                    str += string;
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
