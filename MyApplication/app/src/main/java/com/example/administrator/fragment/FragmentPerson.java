package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.CollectionActivity;
import com.example.administrator.myapplication.LoginVerifyActivity;
import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.PersonalAboutActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.RegisterActivity;
import com.example.administrator.myapplication.SetActivity;
import com.example.administrator.ui.Utils;

/**
 * Created by Administrator on 2016/11/22.
 * 个人中心fragment
 */
public class FragmentPerson extends Fragment{
    private View view;
    private RelativeLayout title1;
    private RelativeLayout title2;
    private RelativeLayout title3;
    private RelativeLayout title4;
    private Button mLogin;
    private Button mRegister;
    private RelativeLayout mRlaymiddle;
    private LinearLayout mUsername;
    private LinearLayout mLlaylogin;
    private LinearLayout mLlayBottom;
    private TextView mLlayname;
    private Button mExitLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_personal,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getViews();
        if (Utils.isTrue == 2){
            mRlaymiddle.setVisibility(View.GONE);
            mUsername.setVisibility(View.VISIBLE);
            mLlaylogin.setVisibility(View.VISIBLE);
            mLlayBottom.setVisibility(View.GONE);
            mLlayname.setText(Utils.username);
        }else {
            mRlaymiddle.setVisibility(View.VISIBLE);
            mUsername.setVisibility(View.GONE);
            mLlaylogin.setVisibility(View.GONE);
            mLlayBottom.setVisibility(View.VISIBLE);
        }

        setListener();

    }

    private void setListener() {
        title1.setOnClickListener(myListener);
        title2.setOnClickListener(myListener);
        title3.setOnClickListener(myListener);
        title4.setOnClickListener(myListener);
        mLogin.setOnClickListener(myListener);
        mRegister.setOnClickListener(myListener);
        mExitLogin.setOnClickListener(myListener);
    }

    public void getViews() {
        //收藏
        title1 = (RelativeLayout)view.findViewById(R.id.personal_rl_title1);
        //设置
        title2 = (RelativeLayout)view.findViewById(R.id.personal_rl_title2);
        //关于
        title3 = (RelativeLayout)view.findViewById(R.id.personal_rl_title3);
        //反馈
        title4 = (RelativeLayout)view.findViewById(R.id.personal_rl_title4);
        //登录
        mLogin = (Button)view.findViewById(R.id.personal_btn_login);
        //注册
        mRegister = (Button)view.findViewById(R.id.personal_btn_register);
        //登录和注册view
        mRlaymiddle = (RelativeLayout)view.findViewById(R.id.personal_rl_middle);
        //用户名view
        mUsername = (LinearLayout)view.findViewById(R.id.personal_Llay_username);
        //personal_Llay_login显示退出登录
        mLlaylogin = (LinearLayout)view.findViewById(R.id.personal_Llay_login);
        //退出登录
        mExitLogin = (Button)view.findViewById(R.id.personal_Btn_exitLogin);
        //personal_Llay_bottom
        mLlayBottom = (LinearLayout)view.findViewById(R.id.personal_Llay_bottom);
        //用户名
        mLlayname = (TextView)view.findViewById(R.id.personal_Llay_Tv_username);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.personal_rl_title1:
                    Intent intent1 = new Intent(getActivity(), CollectionActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.personal_rl_title2:
                    Intent intent2 = new Intent(getActivity(), SetActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.personal_rl_title3:
                    Intent intent6 = new Intent(getActivity(), PersonalAboutActivity.class);
                    startActivity(intent6);
                    break;
                case R.id.personal_rl_title4:
                    break;
                case R.id.personal_btn_login:
                    Intent intent3 = new Intent(getActivity(), LoginVerifyActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.personal_btn_register:
                    Intent intent4 = new Intent(getActivity(), RegisterActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.personal_Btn_exitLogin:
                    Utils.username = null;
                    Utils.isTrue = 1;
                    Intent intent5 = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent5);
                    break;
            }
        }
    };
}
