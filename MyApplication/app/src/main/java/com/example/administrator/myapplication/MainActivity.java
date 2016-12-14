package com.example.administrator.myapplication;

/**
 * 首页
 */

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.fragment.FragmentClassify;
import com.example.administrator.fragment.FragmentHome;
import com.example.administrator.fragment.FragmentPerson;
import com.example.administrator.ui.Utils;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;


public class MainActivity extends Activity {

    private LinearLayout mLlayHome;
    private LinearLayout mLlayClassify;
    private LinearLayout mLlayPerson;
    private FragmentHome mFragHome;
    private FragmentClassify mFragClassify;
    private FragmentPerson mFragPerson;
    private RelativeLayout mRlay;
    private ImageView mLlayHomeIv;
    private ImageView mLlayClassifyIv;
    private ImageView mLlayPersonIv;
    private TextView mLlayHomeTv;
    private TextView mLlayClassifyTv;
    private TextView mLlayPersonTv;
    private BoomMenuButton boomMenuButton;
    private boolean autoDismiss;
    private TextView mTvSearch;
    private LinearLayout mLayTop;
    private LinearLayout mLaySearch;
    private TextView mTvPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取界面的控件
        getViews();
        //注册事件监听器
        setListener();
        //加载BoomMenu
        initBoomMenu();

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
        mLaySearch.setVisibility(View.VISIBLE);
        mTvPerson.setVisibility(View.GONE);
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
        mLaySearch.setVisibility(View.VISIBLE);
        mTvPerson.setVisibility(View.GONE);
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
        mLaySearch.setVisibility(View.GONE);
        mTvPerson.setVisibility(View.VISIBLE);
        //5、执行更改
        transaction.commit();
    }

    private void setListener() {
        mLlayHome.setOnClickListener(myListener);
        mLlayClassify.setOnClickListener(myListener);
        mLlayPerson.setOnClickListener(myListener);
        mTvSearch.setOnClickListener(myListener);
    }

    public void getViews() {
        mLlayHome = (LinearLayout)findViewById(R.id.main_Llay_home);
        mLlayClassify = (LinearLayout)findViewById(R.id.main_Llay_classify);
        mLlayPerson = (LinearLayout)findViewById(R.id.main_Llay_person);
        mRlay = (RelativeLayout)findViewById(R.id.main_Rlay);
        mLaySearch = (LinearLayout)findViewById(R.id.main_Llay_search);
        mLlayHomeIv = (ImageView)findViewById(R.id.main_Llay_home_Iv);
        mLlayClassifyIv = (ImageView)findViewById(R.id.main_Llay_classify_Iv);
        mLlayPersonIv = (ImageView)findViewById(R.id.main_Llay_person_Iv);
        mLlayHomeTv = (TextView)findViewById(R.id.main_Llay_home_Tv);
        mLlayClassifyTv = (TextView)findViewById(R.id.main_Llay_classify_Tv);
        mLlayPersonTv = (TextView)findViewById(R.id.main_Llay_person_Tv);
        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
        mTvSearch = (TextView)findViewById(R.id.main_Tv_search);
        mTvPerson = (TextView)findViewById(R.id.main_Tv_person);
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
                case R.id.main_Tv_search:
                    Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                    startActivity(intent);

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

    /**
     * 初始化加载BoomMenu
     */
    public void initBoomMenu(){
        assert boomMenuButton != null;
        boomMenuButton.setButtonEnum(ButtonEnum.TextOutsideCircle);  //设置文字为外部样式
        boomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);    //设置悬浮按钮内部排列样式
        boomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_1);   //设置点击后按钮排列样式
        boomMenuButton.setShadowColor(Color.parseColor("#66000000"));//设置按钮阴影
        int[] iamgeResource = new int[]{
                R.drawable.icon_name, R.drawable.icon_material, R.drawable.icon_time
        };
        final String [] buttonText = new String[]{
                "按名称搜索", "按食材搜索", "按时间搜索"
        };
        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        for(int i = 0; i <boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder mBuilder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(iamgeResource[i])  //设置按钮图片
                    .normalText(buttonText[i])  //设置文字
                    .textSize(18) //设置字体大小
                    .textHeight(80)  //设置字体区域高度
                    .normalColor(Color.argb(255, 249, 197, 21))  //设置按钮颜色
                    .highlightedColor(Color.argb(255, 255, 112, 85))  //设置按钮点击颜色
                    .unableColor(Color.BLACK); //设置按钮禁止点击颜色
            /**
             * 设置字体区域宽度
             * 如果屏幕宽度小于等于720，则设置宽度为150；否则为270
             */
            if(dm.widthPixels <=720 ){
                mBuilder.textWidth(150);
            }else {
                mBuilder.textWidth(270);
            }
            boomMenuButton.addBuilder(mBuilder);
        }
        /**
         * 点击事件监听
         */
        boomMenuButton.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                switch (index){
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
                    default:
                        break;
                }
            }

            @Override
            public void onBackgroundClick() { }

            @Override
            public void onBoomWillHide() { }

            @Override
            public void onBoomDidHide() { }

            @Override
            public void onBoomWillShow() { }

            @Override
            public void onBoomDidShow() { }
        });
    }

    /**
     * 返回键 监听事件
     * 当BoomMenu开启时，关闭BoomMenu；否则关闭应用
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(boomMenuButton.isBoomed()){
                boomMenuButton.reboom();
            }else{
                finish();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
