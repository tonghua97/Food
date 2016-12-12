package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.fragment.FragmentClassify;
import com.example.administrator.fragment.FragmentHome;
import com.example.administrator.fragment.FragmentPerson;
import com.example.administrator.ui.Utils;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;


public class MainActivity extends Activity {

    private LinearLayout mLlayHome;
    private LinearLayout mLlayClassify;
    private LinearLayout mLlayPerson;
    private FragmentHome mFragHome;
    private FragmentClassify mFragClassify;
    private FragmentPerson mFragPerson;
    private RelativeLayout mRlay;
    private RelativeLayout mRlayTop;
    private ImageView mLlayHomeIv;
    private ImageView mLlayClassifyIv;
    private ImageView mLlayPersonIv;
    private TextView mLlayHomeTv;
    private TextView mLlayClassifyTv;
    private TextView mLlayPersonTv;
    private BoomMenuButton boomMenuButton;
    private boolean autoDismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取界面的控件
        getViews();

        //注册事件监听器
        setListener();

        //设置默认的页面
        switch (Utils.utils){
            case 1:
                setDefaultPage1();
                break;
            case 2:
                setDefaultPage2();
                break;
            case 3:
                setDefaultPage3();
                break;
        }
    }

    /**
     * 显示首页
     */
    private void setDefaultPage1() {
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
        mRlayTop.setVisibility(View.VISIBLE);
        //5、执行更改
        transaction.commit();
    }

    /**
     * 显示分类页
     */
    private void setDefaultPage2() {
        //1、获取一个fragmentManager的对象
        FragmentManager fm = getFragmentManager();
        //2、获取fragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        mFragClassify = new FragmentClassify();
        //3、设置页面
        transaction.replace(R.id.main_Flay_center,mFragClassify);
        //4、设置按钮选中状态
        mLlayClassifyIv.setImageResource(R.drawable.icon_classify_down);
        mLlayClassifyTv.setTextColor(getResources().getColor(R.color.yellow));
        mRlayTop.setVisibility(View.VISIBLE);
        //5、执行更改
        transaction.commit();
    }

    /**
     * 显示个人中心页面
     */
    private void setDefaultPage3() {
        //1、获取一个fragmentManager的对象
        FragmentManager fm = getFragmentManager();
        //2、获取fragmentTransaction对象
        FragmentTransaction transaction = fm.beginTransaction();
        mFragPerson = new FragmentPerson();
        //3、设置页面
        transaction.replace(R.id.main_Flay_center,mFragPerson);
        //4、设置按钮选中状态
        mLlayPersonIv.setImageResource(R.drawable.icon_person_down);
        mLlayPersonTv.setTextColor(getResources().getColor(R.color.yellow));
        mRlayTop.setVisibility(View.GONE);
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
        mRlay = (RelativeLayout)findViewById(R.id.main_Rlay);
        mRlayTop = (RelativeLayout)findViewById(R.id.main_Rlay_top);
        mLlayHomeIv = (ImageView)findViewById(R.id.main_Llay_home_Iv);
        mLlayClassifyIv = (ImageView)findViewById(R.id.main_Llay_classify_Iv);
        mLlayPersonIv = (ImageView)findViewById(R.id.main_Llay_person_Iv);
        mLlayHomeTv = (TextView)findViewById(R.id.main_Llay_home_Tv);
        mLlayClassifyTv = (TextView)findViewById(R.id.main_Llay_classify_Tv);
        mLlayPersonTv = (TextView)findViewById(R.id.main_Llay_person_Tv);
        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
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
                    setMenuItemColor();
                    setDefaultPage1();
                    break;
                case R.id.main_Llay_classify:
                    setMenuItemColor();
                    setDefaultPage2();

                    break;
                case R.id.main_Llay_person:
                    setMenuItemColor();
                    setDefaultPage3();

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
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Use a param to record whether the boom button has been initialized
        // Because we don't need to init it again when onResume()

        Drawable[] subButtonDrawables = new Drawable[3];
        int[] drawablesResource = new int[]{
                R.drawable.icon_home_normal,
                R.drawable.icon_home_normal,
                R.drawable.icon_home_normal
        };
        for (int i = 0; i < 3; i++)
            subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        String[] subButtonTexts = new String[]{"按菜名搜索", "按食材搜索", "按时间搜索"};

        int[][] subButtonColors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            subButtonColors[i][1] = ContextCompat.getColor(this, R.color.orange);
            subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
        }
        new BoomMenuButton.Builder()
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.icon_home_normal), subButtonColors[0], "按菜名搜索")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.icon_home_normal), subButtonColors[0], "按食材搜索")
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.icon_home_normal), subButtonColors[0], "按时间搜索")
                .button(ButtonType.CIRCLE)
                .boom(BoomType.PARABOLA)
                .place(PlaceType.CIRCLE_3_1)
                .rotateDegree(0)
                .subButtonTextColor(ContextCompat.getColor(this, R.color.black))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .init(boomMenuButton)
                .setAutoDismiss(autoDismiss = false);
                boomMenuButton.setOnSubButtonClickListener(new BoomMenuButton.OnSubButtonClickListener() {
                    @Override
                    public void onClick(int buttonIndex) {
                        // 返回被点击的子按钮下标
                        switch (buttonIndex){
                            case 0:
                                Intent i = new Intent(MainActivity.this,SearchNameActivity.class);
                                startActivity(i);
                                break;
                            case 1:
                                Intent i1 = new Intent(MainActivity.this,SearchMaterialActivity.class);
                                startActivity(i1);
                                break;
                            case 2:
                                Intent i2 = new Intent(MainActivity.this,SearchTimeActivity.class);
                                startActivity(i2);
                                break;
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (boomMenuButton.isClosed()) {
            super.onBackPressed();
        } else {
            boomMenuButton.dismiss();
        }
    }
}
