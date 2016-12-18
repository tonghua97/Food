package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 刘博园 on 16.12.24.
 */
public class Personal_setting_EmailActivity extends Activity {
    private Button mBtnBack;
    private Button mBtnSave;
    private EditText mEtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting_mail);
        //获取控件
        getViews();
        //设置监听
        setListener();
        //保存邮箱的修改
        setEmail();
    }
    /**
     * 获取控件
     */
    private void getViews(){
        mBtnBack = (Button)findViewById(R.id.Email_Bt_Back);
        mBtnSave = (Button)findViewById(R.id.Email_Bt_Save);
        mEtEmail = (EditText)findViewById(R.id.Email_Et_Email);
    }

    /**
     * 设置监听
     */
    private void setListener(){
        MyListener listener = new MyListener();
        mBtnBack.setOnClickListener(listener);
        mBtnSave.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //返回键
                case R.id.Email_Bt_Back:
                    finish();
                    break;
                //保存用户名的修改
                case R.id.Email_Bt_Save:
                    SharedPreferences spf = getSharedPreferences("EMAIL_EDIT", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("EMAIL",mEtEmail.getText().toString());
                    editor.commit();
                    Toast.makeText(Personal_setting_EmailActivity.this,"电子邮箱修改并保存成功！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Personal_setting_EmailActivity.this,SetActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 保存电子邮箱的修改
     */
    private void setEmail(){
        SharedPreferences spf = getSharedPreferences("EMAIL_EDIT",Context.MODE_PRIVATE);
        String Email = spf.getString("EMAIL","");
        mEtEmail.setText(Email);
    }
}
