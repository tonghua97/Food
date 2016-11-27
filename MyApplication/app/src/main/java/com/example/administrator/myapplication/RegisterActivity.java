package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 注册页
 */

public class RegisterActivity extends Activity {

    private TextView mTvBack;
    private Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //获取控件
        getViews();
        //设置监听
        setListener();
    }

    private void setListener() {
        mTvBack.setOnClickListener(myListener);
        mRegister.setOnClickListener(myListener);
    }

    public void getViews() {
        //返回
        mTvBack = (TextView)findViewById(R.id.register_Tv_back);
        //注册
        mRegister = (Button)findViewById(R.id.register_Btn_register);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.register_Tv_back:
                    finish();
                    break;
                case R.id.register_Btn_register:
                    Intent intent = new Intent(RegisterActivity.this,LoginVerifyActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };
}
