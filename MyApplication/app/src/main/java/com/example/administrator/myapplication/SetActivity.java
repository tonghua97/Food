package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetActivity extends Activity {

    private Button BtBack;
    private Context context;
    private RelativeLayout mSetPwd;
    private RelativeLayout mSetName;
    private TextView mUname;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);

        //获取控件
        getViews();

        //设置监听
        setListener();
        //设置数据
        setData();
    }

    private void setData() {
        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        name = spf.getString("username","");
        mUname.setText(name);
    }

    private void setListener() {
        BtBack.setOnClickListener(myListener);
        mSetPwd.setOnClickListener(myListener);
        mSetName.setOnClickListener(myListener);
    }

    public void getViews() {
        //返回
        BtBack = (Button)findViewById(R.id.Setting_Bt_Back);
        //修改密码
        mSetPwd = (RelativeLayout)findViewById(R.id.setting_Rlay_pwdedit);
        //修改用户名
        mSetName = (RelativeLayout)findViewById(R.id.setting_Rlay_unameedit);
        //用户名
        mUname = (TextView)findViewById(R.id.setting_Tv_username);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Setting_Bt_Back:
                    finish();
                    break;
                case R.id.setting_Rlay_pwdedit:
                    Intent intent = new Intent(SetActivity.this,Personal_setting_PwdEditActivity.class);
                    startActivity(intent);
                    break;
                case R.id.setting_Rlay_unameedit:
                    Intent intent2 = new Intent(SetActivity.this,Personal_setting_UnameEditActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    };

}
