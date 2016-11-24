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
 */
public class PickfundetailActivity extends Activity {
    private TextView tvtitle,tvcontent;         //拾趣文章标题,文章内容
    private Button btnback;                  //返回按钮
    private ImageView Imgrecipe,collect;    //文章图片、收藏
    int[] images = new int[]{R.drawable.img_collect_unclick, R.drawable.img_collect};
    int currentImg = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickfundetail);

        tvtitle = (TextView) findViewById(R.id.Tv_pickfundetail_title);
        btnback = (Button) findViewById(R.id.Btn_pickfundetail_back);
        Imgrecipe = (ImageView) findViewById(R.id.Iv_pickfundetail_recipeimg);
        tvcontent = (TextView) findViewById(R.id.Tv_pickfundetail_content);
        collect = (ImageView) findViewById(R.id.Iv_pickfundetail_collect);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_backtopickfun = new Intent();
                //跳转回“拾趣”列表页面
                intent_backtopickfun.setClass(getBaseContext(), FragmentHomePickfun.class);
                startActivity(intent_backtopickfun);
            }
        });

        collect.setImageResource(images[0]);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改变Imageview里显示的图片
                collect.setImageResource(images[++currentImg % images.length]);
            }
        });
    }
}
