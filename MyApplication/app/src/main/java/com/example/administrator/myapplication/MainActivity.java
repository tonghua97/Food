package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.fragment.FragmentClassify;
import com.example.administrator.fragment.FragmentHome;
import com.example.administrator.fragment.FragmentPerson;


public class MainActivity extends Activity {

    private LinearLayout mLlayHome;
    private LinearLayout mLlayClassify;
    private LinearLayout mLlayPerson;
    private FragmentHome mFragHome;
    private FragmentClassify mFragClassify;
    private FragmentPerson mFragPerson;
    private LinearLayout mRlay;
    private RelativeLayout mRlayTop;
    private ImageView mLlayHomeIv;
    private ImageView mLlayClassifyIv;
    private ImageView mLlayPersonIv;
    private TextView mLlayHomeTv;
    private TextView mLlayClassifyTv;
    private TextView mLlayPersonTv;

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
        mFragHome = new FragmentHome();
        //3、设置页面
        transaction.replace(R.id.main_Flay_center,mFragHome);
        //4、设置按钮选中状态
        mLlayHomeIv.setImageResource(R.drawable.icon_home_down);
        mLlayHomeTv.setTextColor(getResources().getColor(R.color.yellow));
        //5、执行更改
        transaction.commit();
    }

    private void setListener() {
        mLlayHome.setOnClickListener(myListener);
        mLlayClassify.setOnClickListener(myListener);
        mLlayPerson.setOnClickListener(myListener);
    }

    public void getViews() {
        mLlayHome = (LinearLayout)findViewById(R.id.main_Llay_home);
        mLlayClassify = (LinearLayout)findViewById(R.id.main_Llay_classify);
        mLlayPerson = (LinearLayout)findViewById(R.id.main_Llay_person);
        mRlay = (LinearLayout)findViewById(R.id.main_Rlay);
        mRlayTop = (RelativeLayout)findViewById(R.id.main_Rlay_top);
        mLlayHomeIv = (ImageView)findViewById(R.id.main_Llay_home_Iv);
        mLlayClassifyIv = (ImageView)findViewById(R.id.main_Llay_classify_Iv);
        mLlayPersonIv = (ImageView)findViewById(R.id.main_Llay_person_Iv);
        mLlayHomeTv = (TextView)findViewById(R.id.main_Llay_home_Tv);
        mLlayClassifyTv = (TextView)findViewById(R.id.main_Llay_classify_Tv);
        mLlayPersonTv = (TextView)findViewById(R.id.main_Llay_person_Tv);
    }


    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //1、获取一个fragmentManager的对象
            FragmentManager fm = getFragmentManager();
            //2、获取fragmentTransaction对象
            FragmentTransaction transaction = fm.beginTransaction();

            switch (view.getId()){
                case R.id.main_Llay_home:
                    if (mFragHome == null){
                        mFragHome = new FragmentHome();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_Flay_center,mFragHome);
                    setMenuItemColor();
                    mLlayHomeIv.setImageResource(R.drawable.icon_home_down);
                    mLlayHomeTv.setTextColor(getResources().getColor(R.color.yellow));
                    mRlayTop.setVisibility(View.VISIBLE);

                    break;
                case R.id.main_Llay_classify:
                    if (mFragClassify == null){
                        mFragClassify = new FragmentClassify();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_Flay_center,mFragClassify);
                    setMenuItemColor();
                    mLlayClassifyIv.setImageResource(R.drawable.icon_classify_down);
                    mLlayClassifyTv.setTextColor(getResources().getColor(R.color.yellow));
                    mRlayTop.setVisibility(View.VISIBLE);

                    break;
                case R.id.main_Llay_person:
                    if (mFragPerson == null){
                        mFragPerson = new FragmentPerson();
                    }

                    //3、设置页面
                    transaction.replace(R.id.main_Flay_center,mFragPerson);
                    setMenuItemColor();
                    mLlayPersonIv.setImageResource(R.drawable.icon_person_down);
                    mLlayPersonTv.setTextColor(getResources().getColor(R.color.yellow));
                    mRlayTop.setVisibility(View.GONE);

                    break;
            }

            //4、执行更改
            transaction.commit();
            //页面刷新
            mRlay.invalidate();
        }
    };

    /**
     * 设置底栏菜单选项默认状态
     */
    private void setMenuItemColor(){
        //设置图片
        mLlayHomeIv.setImageResource(R.drawable.icon_home_normal);
        mLlayClassifyIv.setImageResource(R.drawable.icon_classify_normal);
        mLlayPersonIv.setImageResource(R.drawable.icon_person_normal);
        //设置字体颜色
        mLlayHomeTv.setTextColor(getResources().getColor(R.color.black));
        mLlayClassifyTv.setTextColor(getResources().getColor(R.color.black));
        mLlayPersonTv.setTextColor(getResources().getColor(R.color.black));

    }
}
