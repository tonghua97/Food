package com.example.administrator.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/11/22.
 */
public class FragmentShouye extends Fragment{
    private View view;
    private TextView Tv1;
    private TextView Tv2;
    private TextView Tv3;
    private FragmentShouyeTuijian mTuijian;
    private FragmentShouyeShiqu mShiqu;
    private FragmentShouyePaihang mPaihang;
    private LinearLayout Llay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment_shouye,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取控件
        getViews();

        //注册事件监听器
        setListener();

        //设置默认的页面(fragment页面)
        setDefaultPage();
    }

    private void setDefaultPage() {
        //1、获取一个fragmentManager的对象
        FragmentManager fm = getFragmentManager();
        //2、获取fragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        mTuijian = new FragmentShouyeTuijian();
        //3、设置页面
        transaction.replace(R.id.main_fragment_shouye_Flay,mTuijian);
        Tv1.setTextColor(Color.parseColor("#FFFF00"));
        //4、执行更改
        transaction.commit();
    }

    private void setListener() {
        Tv1.setOnClickListener(myListener);
        Tv2.setOnClickListener(myListener);
        Tv3.setOnClickListener(myListener);
    }

    public void getViews() {
        Tv1 = (TextView)view.findViewById(R.id.main_fragment_shouye_Tv1);
        Tv2 = (TextView)view.findViewById(R.id.main_fragment_shouye_Tv2);
        Tv3 = (TextView)view.findViewById(R.id.main_fragment_shouye_Tv3);
        Llay = (LinearLayout)view.findViewById(R.id.main_fragment_shouye_Llay);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //1、获取一个fragmentManager的对象
            FragmentManager fm = getFragmentManager();
            //2、获取fragmentTransaction对象
            FragmentTransaction transaction = fm.beginTransaction();

            Tv1.setTextColor(Color.BLACK);
            Tv2.setTextColor(Color.BLACK);
            Tv3.setTextColor(Color.BLACK);

            switch (view.getId()){
                case R.id.main_fragment_shouye_Tv1:
                    if (mTuijian == null){
                        mTuijian = new FragmentShouyeTuijian();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_fragment_shouye_Flay,mTuijian);
                    Tv1.setTextColor(Color.parseColor("#FFFF00"));

                    break;
                case R.id.main_fragment_shouye_Tv2:
                    if (mShiqu == null){
                        mShiqu = new FragmentShouyeShiqu();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_fragment_shouye_Flay,mShiqu);
                    Tv2.setTextColor(Color.parseColor("#FFFF00"));

                    break;
                case R.id.main_fragment_shouye_Tv3:
                    if (mPaihang == null){
                        mPaihang = new FragmentShouyePaihang();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_fragment_shouye_Flay,mPaihang);
                    Tv3.setTextColor(Color.parseColor("#FFFF00"));

                    break;
            }

            //4、执行更改
            transaction.commit();
            //页面刷新
            Llay.invalidate();
        }
    };
}
