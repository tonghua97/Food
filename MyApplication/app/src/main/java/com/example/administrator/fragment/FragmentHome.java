package com.example.administrator.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.adapter.PageFragmentAdapter;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 * 首页fragment
 */
public class FragmentHome extends Fragment{
    private ViewPager viewPager;
    private PageFragmentAdapter adapter;
    private List<Fragment> fragmentList;
    private View view;
    private TextView Tv1;
    private TextView Tv2;
    private TextView Tv3;
    private FragmentHomeRecommend mTuijian;
    private FragmentHomePickfun mShiqu;
    private FragmentHomeRank mPaihang;
    private LinearLayout Llay;
    private FragmentHomeRecommend fragmentHomeRecommend;
    private FragmentHomePickfun fragmentHomePickfun;
    private FragmentHomeRank fragmentHomeRank;
    private int currentIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment_home,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取控件
        getViews();

        //注册事件监听器
        setListener();

        initView();

        //设置默认的页面(fragment页面)
//        setDefaultPage();
    }

    private void initView() {
        FragmentHomeRecommend fragmentHomeRecommend = new FragmentHomeRecommend();
        FragmentHomePickfun fragmentHomePickfun = new FragmentHomePickfun();
        FragmentHomeRank fragmentHomeRank = new FragmentHomeRank();
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(fragmentHomeRecommend);
        fragmentList.add(fragmentHomePickfun);
        fragmentList.add(fragmentHomeRank);

        adapter=new PageFragmentAdapter(getChildFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        Tv1.setTextColor(getResources().getColor(R.color.yellow));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        Tv1.setTextColor(getResources().getColor(R.color.yellow));
                        break;
                    case 1:
                        Tv2.setTextColor(getResources().getColor(R.color.yellow));
                        break;
                    case 2:
                        Tv3.setTextColor(getResources().getColor(R.color.yellow));
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
        Tv3.setTextColor(Color.BLACK);
    }

//    private void setDefaultPage() {
//        //1、获取一个fragmentManager的对象
//        FragmentManager fm = getFragmentManager();
//        //2、获取fragmentTransaction对象
//        FragmentTransaction transaction = fm.beginTransaction();
//        mTuijian = new FragmentHomeRecommend();
//        //3、设置页面
//        transaction.replace(R.id.main_fragment_shouye_Flay,mTuijian);
//        Tv1.setTextColor(getResources().getColor(R.color.yellow));
//        //4、执行更改
//        transaction.commit();
//    }

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
        viewPager=(ViewPager)view.findViewById(R.id.vpNewsList);
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
                    viewPager.setCurrentItem(0);
//                    if (mTuijian == null){
//                        mTuijian = new FragmentHomeRecommend();
//                    }
//
//                    //3、设置页面
//                    transaction.replace(R.id.main_fragment_shouye_Flay,mTuijian);
                    Tv1.setTextColor(getResources().getColor(R.color.yellow));

                    break;
                case R.id.main_fragment_shouye_Tv2:
                    viewPager.setCurrentItem(1);
//                    if (mShiqu == null){
//                        mShiqu = new FragmentHomePickfun();
//                    }
//
//                    //3、设置页面
//                    transaction.replace(R.id.main_fragment_shouye_Flay,mShiqu);
                    Tv2.setTextColor(getResources().getColor(R.color.yellow));

                    break;
                case R.id.main_fragment_shouye_Tv3:
                    viewPager.setCurrentItem(2);
//                    if (mPaihang == null){
//                        mPaihang = new FragmentHomeRank();
//                    }
//
//                    //3、设置页面
//                    transaction.replace(R.id.main_fragment_shouye_Flay,mPaihang);
                    Tv3.setTextColor(getResources().getColor(R.color.yellow));

                    break;
            }

            //4、执行更改
            transaction.commit();
            //页面刷新
            Llay.invalidate();
        }
    };
}
