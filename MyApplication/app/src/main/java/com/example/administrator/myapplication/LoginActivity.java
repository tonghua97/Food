package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends Activity {

    private TextView mUsername;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取控件
        getViews();
        //设置用户名
        setData();
    }


    private void setData() {
        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        name = spf.getString("username","");
        mUsername.setText(name);
    }

    public void getViews() {
        mUsername = (TextView)findViewById(R.id.login_tv_username);
    }
}
