package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.fragment.FragmentFenlei;
import com.example.administrator.fragment.FragmentShouye;
import com.example.administrator.fragment.FragmentWode;


public class MainActivity extends Activity {

    private LinearLayout mLlayShouye;
    private LinearLayout mLlayFenlei;
    private LinearLayout mLlayWode;
    private FragmentShouye mFragShouye;
    private FragmentFenlei mFragFenlei;
    private FragmentWode mFragWode;
    private LinearLayout mRlay;
    private RelativeLayout mRlayTop;
    private ImageView mLlayShouyeIv;
    private ImageView mLlayFenleiIv;
    private ImageView mLlayWodeIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取界面的控件
        getViews();

        //注册事件监听器
        setListener();

        //设置默认的页面
        setDefaultPage();
    }


    private void setDefaultPage() {
        //1、获取一个fragmentManager的对象
        FragmentManager fm = getFragmentManager();
        //2、获取fragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        mFragShouye = new FragmentShouye();
        //3、设置页面
        transaction.replace(R.id.main_Flay_center,mFragShouye);
        //4、执行更改
        transaction.commit();
    }

    private void setListener() {
        mLlayShouye.setOnClickListener(myListener);
        mLlayFenlei.setOnClickListener(myListener);
        mLlayWode.setOnClickListener(myListener);
    }

    public void getViews() {
        mLlayShouye = (LinearLayout)findViewById(R.id.main_Llay_shouye);
        mLlayFenlei = (LinearLayout)findViewById(R.id.main_Llay_fenlei);
        mLlayWode = (LinearLayout)findViewById(R.id.main_Llay_wode);
        mRlay = (LinearLayout)findViewById(R.id.main_Rlay);
        mRlayTop = (RelativeLayout)findViewById(R.id.main_Rlay_top);
        mLlayShouyeIv = (ImageView)findViewById(R.id.main_Llay_shouye_Iv);
        mLlayFenleiIv = (ImageView)findViewById(R.id.main_Llay_fenlei_Iv);
        mLlayWodeIv = (ImageView)findViewById(R.id.main_Llay_wode_Iv);
    }


    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //1、获取一个fragmentManager的对象
            FragmentManager fm = getFragmentManager();
            //2、获取fragmentTransaction对象
            FragmentTransaction transaction = fm.beginTransaction();

            switch (view.getId()){
                case R.id.main_Llay_shouye:
                    if (mFragShouye == null){
                        mFragShouye = new FragmentShouye();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_Flay_center,mFragShouye);
                    mLlayFenleiIv.setImageResource(R.drawable.icon_classify_normal);
                    mLlayWodeIv.setImageResource(R.drawable.icon_person_normal);
                    mLlayShouyeIv.setImageResource(R.drawable.icon_home_down);
                    mRlayTop.setVisibility(View.VISIBLE);

                    break;
                case R.id.main_Llay_fenlei:
                    if (mFragFenlei == null){
                        mFragFenlei = new FragmentFenlei();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_Flay_center,mFragFenlei);
                    mLlayFenleiIv.setImageResource(R.drawable.icon_classify_down);
                    mLlayWodeIv.setImageResource(R.drawable.icon_person_normal);
                    mLlayShouyeIv.setImageResource(R.drawable.icon_home_normal);
                    mRlayTop.setVisibility(View.VISIBLE);

                    break;
                case R.id.main_Llay_wode:
                    if (mFragWode == null){
                        mFragWode = new FragmentWode();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_Flay_center,mFragWode);
                    mLlayFenleiIv.setImageResource(R.drawable.icon_classify_normal);
                    mLlayWodeIv.setImageResource(R.drawable.icon_person_down);
                    mLlayShouyeIv.setImageResource(R.drawable.icon_home_normal);
                    mRlayTop.setVisibility(View.GONE);

                    break;
            }

            //4、执行更改
            transaction.commit();
            //页面刷新
            mRlay.invalidate();
        }
    };
}
