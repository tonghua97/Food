package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 梁爽 on 16.11.24.
 */
public class Personal_setting_UnameEditActivity extends Activity {
    private Button mBtnClear;
    private Button mBtnBack;
    private Button mBtnSave;
    private EditText mEtUname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting_unameedit);

        //获取控件
        getViews();

        //设置监听
        setListener();

        //保存用户名的修改
        setUname();
    }

    /**
     * 获取控件
     */
    private void getViews(){
        mBtnClear = (Button)findViewById(R.id.UnameEdit_btn_clear);
        mBtnBack = (Button)findViewById(R.id.UnameEdit_Bt_Back);
        mBtnSave = (Button)findViewById(R.id.UnameEdit_Bt_Save);

        mEtUname = (EditText)findViewById(R.id.UnameEdit_Et_Uname);
    }

    /**
     * 设置监听
     */
    private void setListener(){
        MyListener listener = new MyListener();
        mBtnBack.setOnClickListener(listener);
        mBtnSave.setOnClickListener(listener);
        mBtnClear.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
//                返回键
                case R.id.UnameEdit_Bt_Back:
                    finish();
                    break;
//                保存用户名的修改
                case R.id.UnameEdit_Bt_Save:
                    SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("UNAME",mEtUname.getText().toString());
                    editor.commit();
                    Toast.makeText(Personal_setting_UnameEditActivity.this,"用户名修改并保存成功！",Toast.LENGTH_SHORT).show();
                    break;
//                清空用户名
                case R.id.UnameEdit_btn_clear:
                    mEtUname.setText(" ");
                    break;
            }
        }
    }

    /**
     * 保存用户名的修改
     */
    private void setUname(){
        SharedPreferences spf = getSharedPreferences("UNAME_EDIT",Context.MODE_PRIVATE);
        String Uname = spf.getString("UNAME","");
        mEtUname.setText(Uname);
    }
}
