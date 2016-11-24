package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by 梁爽 on 16.11.24.
 * 说明：“设置”页面的编码
 */

public class Personal_settingActivity extends Activity {

    private Button mBtBack;             //返回键
    private Button mBtAvatarEdit;      //头像修改
    private Button mBtUnameEdit;        //用户名修改
    private Button mBtPwdEdit;         //密码修改

    private Spinner mSpGender;         //性别选择下拉列表
    private ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);

        //获取控件
        getViews();

        //设置监听
        setListener();

        //设置下拉列表--用户性别选择
        setSpinner();
    }

    /**
     * 获取控件
     */
    private void getViews(){
        mBtBack = (Button)findViewById(R.id.Setting_Bt_Back);
        mBtAvatarEdit = (Button)findViewById(R.id.Setting_Bt_avatarEdit);
        mBtUnameEdit = (Button)findViewById(R.id.Setting_Bt_UserNameEdit);
        mBtPwdEdit = (Button)findViewById(R.id.Setting_Bt_UserPasswordEdit);

        mSpGender = (Spinner)findViewById(R.id.Setting_Sp_Gender);
    }

    /**
     * 设置监听器
     */
    private void setListener(){
        MyListener listener = new MyListener();
        mBtBack.setOnClickListener(listener);
        mBtAvatarEdit.setOnClickListener(listener);
        mBtUnameEdit.setOnClickListener(listener);
        mBtPwdEdit.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Setting_Bt_Back:
                    /*返回上一页*/
                    finish();
                    break;
                case R.id.Setting_Bt_avatarEdit:
                    /*跳转到头像修改页面*/
//                    Intent intent1 = new Intent(Personal_settingActivity.this,Personal_setting_PwdEditActivity.class);
//                    startActivity(intent1);
                    break;
                case R.id.Setting_Bt_UserNameEdit:
                     /*跳转到用户名修改页面*/
                    Intent intent2 = new Intent(Personal_settingActivity.this,Personal_setting_UnameEditActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.Setting_Bt_UserPasswordEdit:
                     /*跳转到密码修改页面*/
                    Intent intent3 = new Intent(Personal_settingActivity.this,Personal_setting_PwdEditActivity.class);
                    startActivity(intent3);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 下拉列表的设置
     */
    private void setSpinner(){
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.setting_gender);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        mSpGender .setAdapter(adapter);
        //添加事件Spinner事件监听
        mSpGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //保存用户的性别选择
                mSpGender.setSelection(pos,true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    }

}
