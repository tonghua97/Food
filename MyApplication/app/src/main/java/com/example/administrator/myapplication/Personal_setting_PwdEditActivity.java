package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 梁爽 on 16.11.24.
 */
public class Personal_setting_PwdEditActivity extends Activity {

    private Button mBtBack;
    private Button mBtSave;
    private EditText mEtOldpwd;
    private EditText getmEtNewpwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting_pwdedit);

        //获取控件
        getViews();

        //设置监听
        setListener();
    }

    /**
     * 获取控件
     */
    private void getViews(){
        mBtBack = (Button)findViewById(R.id.PwdEdit_Bt_Back);
        mBtSave = (Button)findViewById(R.id.PwdEdit_Bt_Save);

        mEtOldpwd = (EditText)findViewById(R.id.PwdEdit_Et_oldpwd);
        getmEtNewpwd = (EditText)findViewById(R.id.PwdEdit_Et_newpwd);
    }
    private void setListener(){
        MyListener listener = new MyListener();
        mBtBack.setOnClickListener(listener);
        mBtSave.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //返回键功能
                case R.id.PwdEdit_Bt_Back:
                    finish();
                    break;
                //保存修改后的密码
                case R.id.PwdEdit_Bt_Save:
                    //Code:检验旧密码是否正确并保存新密码
                    break;
            }
        }
    }
}
