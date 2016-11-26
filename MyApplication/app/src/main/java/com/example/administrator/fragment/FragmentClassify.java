package com.example.administrator.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/11/22.
 * 分类fragment
 */
public class FragmentClassify extends Fragment{
    private View view;
    private LinearLayout mStyle;
    private LinearLayout mEffect;
    private LinearLayout mTaste;
    private FragmentClassifyCuisine mFragCuisine;
    private FragmentClassifyEffect mFragEffect;
    private FragmentClassifyTaste mFragTaste;
    private LinearLayout mLlay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment_classify,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取控件
        getViews();
        //设置监听
        setListener();
        //设置默认页面
        setDefaultPage();
    }

    private void setDefaultPage() {
        //1、获取一个fragmentManager的对象
        FragmentManager fm = getFragmentManager();
        //2、获取fragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        mFragCuisine = new FragmentClassifyCuisine();
        //3、设置页面
        transaction.replace(R.id.main_fragment_classify_Flay,mFragCuisine);
        //4、执行更改
        transaction.commit();
    }

    private void setListener() {
        mStyle.setOnClickListener(myListener);
        mEffect.setOnClickListener(myListener);
        mTaste.setOnClickListener(myListener);
    }

    public void getViews() {
        mStyle = (LinearLayout)view.findViewById(R.id.main_fragment_classify_style);
        mEffect = (LinearLayout)view.findViewById(R.id.main_fragment_classify_effect);
        mTaste = (LinearLayout)view.findViewById(R.id.main_fragment_classify_taste);
        mLlay = (LinearLayout)view.findViewById(R.id.main_fragment_classify_Llay);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //1、获取一个fragmentManager的对象
            FragmentManager fm = getFragmentManager();
            //2、获取fragmentTransaction对象
            FragmentTransaction transaction = fm.beginTransaction();

            switch (view.getId()){
                case R.id.main_fragment_classify_style:
                    if (mFragCuisine == null){
                        mFragCuisine = new FragmentClassifyCuisine();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_fragment_classify_Flay,mFragCuisine);

                    break;
                case R.id.main_fragment_classify_effect:
                    if (mFragEffect == null){
                        mFragEffect = new FragmentClassifyEffect();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_fragment_classify_Flay,mFragEffect);

                    break;
                case R.id.main_fragment_classify_taste:
                    if (mFragTaste == null){
                        mFragTaste = new FragmentClassifyTaste();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_fragment_classify_Flay,mFragTaste);

                    break;
            }

            //4、执行更改
            transaction.commit();
            //页面刷新
            mLlay.invalidate();
        }
    };
}
