package com.example.administrator.myapplication;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.adapter.PageFragmentAdapter;
import com.example.administrator.fragment.FragmentCollectionPickfun;
import com.example.administrator.fragment.FragmentCollectionRecipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/23.
 * 收藏Activity
 */

public class CollectionActivity extends Activity{
    private ViewPager viewPager;
    private PageFragmentAdapter adapter;
    private List<Fragment> fragmentList;
    private View view;
    private TextView Tv1;
    private TextView Tv2;
    private FragmentCollectionPickfun mCpickfun;
    private FragmentCollectionRecipe mCrecipe;
    private LinearLayout Llay;
    private FragmentCollectionPickfun fragmentCollectionPickfun;
    private FragmentCollectionRecipe fragmentCollectionRecipe;
    private int currentIndex;
    private Button bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        //获取控件
        getViews();

        //注册事件监听器
        setListener();
//
        initView();

    }

    private void initView() {
        FragmentCollectionPickfun fragmentCollectionPickfun = new FragmentCollectionPickfun();
        FragmentCollectionRecipe fragmentCollectionRecipe = new FragmentCollectionRecipe();
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(fragmentCollectionRecipe);
        fragmentList.add(fragmentCollectionPickfun);

        adapter=new PageFragmentAdapter(getFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        Tv2.setTextColor(getResources().getColor(R.color.yellow));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) {
            }
//            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        Tv2.setTextColor(getResources().getColor(R.color.yellow));
                        break;
                    case 1:
                        Tv1.setTextColor(getResources().getColor(R.color.yellow));
                        break;

                }
                currentIndex = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void resetTextView() {
        Tv1.setTextColor(Color.BLACK);
        Tv2.setTextColor(Color.BLACK);
  }


    private void setListener() {
        bt_back.setOnClickListener(myListener);
        Tv1.setOnClickListener(myListener);
        Tv2.setOnClickListener(myListener);
    }

    public void getViews() {
        bt_back = (Button)findViewById(R.id.collection_Bt_Back);
        Tv1 = (TextView)findViewById(R.id.fragment_collection_pickfun_Tv);
        Tv2 = (TextView)findViewById(R.id.fragment_collection_recipe_Tv);
        Llay = (LinearLayout)findViewById(R.id.fragment_collection_Llay);
        viewPager = (ViewPager)findViewById(R.id.collection_fragment_flay);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Tv1.setTextColor(Color.BLACK);
            Tv2.setTextColor(Color.BLACK);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();

            switch (view.getId()){
                case R.id.collection_Bt_Back:
                    finish();
                    break;
                case R.id.fragment_collection_recipe_Tv:
                    viewPager.setCurrentItem(0);
                    Tv2.setTextColor(getResources().getColor(R.color.yellow));
                    break;
                case R.id.fragment_collection_pickfun_Tv:
                    viewPager.setCurrentItem(1);
                    Tv1.setTextColor(getResources().getColor(R.color.yellow));
                    break;
            }

            //4、执行更改
            transaction.commit();
            //页面刷新
            Llay.invalidate();
        }
    };
}

