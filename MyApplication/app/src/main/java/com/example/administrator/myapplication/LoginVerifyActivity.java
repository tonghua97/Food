package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.ui.Utils;

/**
 * 登录页
 */

public class LoginVerifyActivity extends Activity {

    private TextView mTvBack;
    private Button mLogin;
    private EditText mUsername;
    private EditText mPassword;
    private Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verify);

        //获取控件
        getViews();
        //设置监听
        setListener();
    }

    private void setListener() {
        mTvBack.setOnClickListener(myListener);
        mLogin.setOnClickListener(myListener);
        mRegister.setOnClickListener(myListener);
    }

    public void getViews() {
        //返回
        mTvBack = (TextView)findViewById(R.id.login_verify_Tv_back);
        //登录
        mLogin = (Button)findViewById(R.id.login_verify_Btn_login);
        //用户名
        mUsername = (EditText)findViewById(R.id.login_verify_Et_username);
        //密码
        mPassword = (EditText)findViewById(R.id.login_verify_Et_password);
        //注册
        mRegister = (Button)findViewById(R.id.login_verify_btn_register);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_verify_Tv_back:
                    finish();
                    break;
                case R.id.login_verify_Btn_login:
                    SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("username",mUsername.getText().toString());
                    editor.commit();
                    Utils.username = mUsername.getText().toString();
                    Utils.utils = 3;
                    Utils.isTrue = 2;
                    Intent intent = new Intent(LoginVerifyActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.login_verify_btn_register:
                    Intent intent2 = new Intent(LoginVerifyActivity.this,RegisterActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
            }
        }
    };
}
