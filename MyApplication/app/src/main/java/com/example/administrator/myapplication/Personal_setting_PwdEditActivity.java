package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 梁爽 on 16.11.24.
 */
public class Personal_setting_PwdEditActivity extends Activity {

    private Button mBtBack;             //返回键
    private Button mBtSave;             //保存密码的修改
    private EditText mEtOldpwd;         //旧密码
    private EditText getmEtNewpwd;      //新密码

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

    /**
     * 设置监听
     */
    private void setListener(){
        MyListener listener = new MyListener();
        mBtBack.setOnClickListener(listener);
        mBtSave.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                /*返回键功能*/
                case R.id.PwdEdit_Bt_Back:
                    finish();
                    break;

                /*保存修改后的密码*/
                case R.id.PwdEdit_Bt_Save:
                    //检验旧密码是否正确

                    /*检验新密码的有效性*/
                    //1.旧密码输入正确&&新密码与旧密码相同
//                    if (mEtOldpwd.getText().toString().equals(getmEtNewpwd.getText().toString()) && ){
//                        //创建对话框创建器
//                        AlertDialog.Builder ab = new AlertDialog.Builder(Personal_setting_PwdEditActivity.this);
//                        //设置对话框
//                        ab.setTitle("提示");
//                        ab.setMessage("新密码与旧密码相同，请重新设置");
//                        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Personal_setting_PwdEditActivity.this.finish();
//                            }
//                        });
//                        //ab.setNegativeButton("取消",null);
//                        ab.create().show();
//                    }else if(/*旧密码输入错误*/){         //2.旧密码输入错误
//                        //创建对话框创建器
//                        AlertDialog.Builder ab = new AlertDialog.Builder(Personal_setting_PwdEditActivity.this);
//                        //设置对话框
//                        ab.setTitle("提示");
//                        ab.setMessage("旧密码输入不正确，请重新输入！");
//                        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Personal_setting_PwdEditActivity.this.finish();
//                            }
//                        });
//                        //ab.setNegativeButton("取消",null);
//                        ab.create().show();
//                    }else {
                           Toast.makeText(Personal_setting_PwdEditActivity.this,"密码修改成功！",Toast.LENGTH_SHORT).show();
//                    }
                    break;
                default:
                    break;
            }
        }
    }
}
