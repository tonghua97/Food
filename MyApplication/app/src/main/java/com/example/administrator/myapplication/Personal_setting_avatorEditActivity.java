package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 梁爽 on 16.11.24.
 */
public class Personal_setting_avatorEditActivity extends Activity {
    private Button mBtnClear;
    private Button mBtnBack;
    private Button mBtnSave;
    private EditText mEtUname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting_avataredit);

        //获取控件
        getViews();

        //设置监听
        setListener();
    }


    /**
     * 获取控件
     */
    private void getViews(){
        mBtnClear = (Button)findViewById(R.id.AvatarEdit_btn_clear);
        mBtnBack = (Button)findViewById(R.id.AvatarEdit_Bt_Back);
        mBtnSave = (Button)findViewById(R.id.AvatarEdit_Bt_Save);

        mEtUname = (EditText)findViewById(R.id.AvatarEdit_Et_Uname);
    }
    /**
     * 设置监听
     */
    private void setListener(){
        MyListener listener = new MyListener();
        mBtnBack.setOnClickListener(listener);
        mBtnSave.setOnClickListener(listener);
        mBtnClear.setOnClickListener(listener);

        //mEtUname.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.AvatarEdit_Bt_Back:
                    finish();
                    break;
                case R.id.AvatarEdit_Bt_Save:

                    break;
                case R.id.AvatarEdit_btn_clear:

                    break;
            }
        }
    }
}
