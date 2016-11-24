package com.example.administrator.myapplication;

import android.app.Activity;
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

    private Button back;             //返回键
    private Button avatarEdit;      //头像修改
    private Button nameEdit;        //用户名修改
    private Button pwdEdit;         //密码修改

    private Spinner gender;         //性别选择下拉列表
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
        back = (Button)findViewById(R.id.Setting_Bt_Back);
        avatarEdit = (Button)findViewById(R.id.Setting_Bt_avatarEdit);
        nameEdit = (Button)findViewById(R.id.Setting_Bt_UserNameEdit);
        pwdEdit = (Button)findViewById(R.id.Setting_Bt_UserPasswordEdit);

        gender = (Spinner)findViewById(R.id.Setting_Sp_Gender);
    }

    /**
     * 设置监听器
     */
    private void setListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        avatarEdit.setOnClickListener(listener);
        nameEdit.setOnClickListener(listener);
        pwdEdit.setOnClickListener(listener);
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
//                    Intent intent1 = new Intent(PersonCenter_SettingActivity.this,Setting_AvatarEditActivity.class);
//                    startActivity(intent1);
                    break;
                case R.id.Setting_Bt_UserNameEdit:
                     /*跳转到用户名修改页面*/
//                    Intent intent2 = new Intent(PersonCenter_SettingActivity.this,Setting_UserNameEditActivity.class);
//                    startActivity(intent2);
                    break;
                case R.id.Setting_Bt_UserPasswordEdit:
                     /*跳转到密码修改页面*/
//                    Intent intent3 = new Intent(PersonCenter_SettingActivity.this,Setting_UserPwdEditActivity.class);
//                    startActivity(intent3);
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
        gender .setAdapter(adapter);
        //添加事件Spinner事件监听
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //保存用户的性别选择
                gender.setSelection(pos,true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    }

}
