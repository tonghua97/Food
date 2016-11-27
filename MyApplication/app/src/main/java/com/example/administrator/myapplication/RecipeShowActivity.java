package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterRecipeStep;
import com.example.administrator.domain.DataRecipe;
import com.example.administrator.domain.DataRecipeStep;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeshow);

        Intent intent = getIntent();
        title = intent.getStringExtra("NAME");

        //获取数据
        getData();
        //获取控件
        getView();
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
}
