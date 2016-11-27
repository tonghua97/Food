package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.fragment.FragmentHomePickfun;

/**
 * Created by 姜佳妮 on 2016/11/24.
 * 拾趣详情页
 */
public class PickfundetailActivity extends Activity {
    private TextView tvtitle,tvcontent;         //拾趣文章标题,文章内容
    private Button btnback;                  //返回按钮
    private ImageView Imgrecipe,collect;    //文章图片、收藏
    private boolean isSelected = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickfundetail);

        //获取控件
        getViews();
        //获取美食名称
        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        //设置tvtitle
        tvtitle.setText(title);
        //设置监听
        setListener();
    }

    private void setListener() {
        btnback.setOnClickListener(myListener);
        collect.setOnClickListener(myListener);
    }

    public void getViews() {
        tvtitle = (TextView) findViewById(R.id.Tv_pickfundetail_title);
        btnback = (Button) findViewById(R.id.Btn_pickfundetail_back);
        Imgrecipe = (ImageView) findViewById(R.id.Iv_pickfundetail_recipeimg);
        tvcontent = (TextView) findViewById(R.id.Tv_pickfundetail_content);
        collect = (ImageView) findViewById(R.id.Iv_pickfundetail_collect);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Btn_pickfundetail_back:
                    finish();
                    break;
                case R.id.Iv_pickfundetail_collect:
                    if (isSelected){
                        collect.setImageResource(R.drawable.img_collect);
                        isSelected = false;
                    }else {
                        collect.setImageResource(R.drawable.img_collect_unclick);
                        isSelected = true;
                    }
                    break;
            }
        }
    };
}

