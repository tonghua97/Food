package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.ui.Urls;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁爽 on 16.11.24.
 */
public class Personal_setting_PwdEditActivity extends Activity {

    private Button mBtBack;             //返回键
    private Button mBtSave;             //保存密码的修改
    private EditText mEtOldpwd;         //旧密码
    private EditText getmEtNewpwd;      //新密码
    private EditText getEtIsNewPwd;
    private String str;
    private String userId;

    private Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Toast.makeText(getApplication(),str,Toast.LENGTH_SHORT).show();
            if (str.equals("0")){
                Toast.makeText(getApplicationContext(),"用户不存在",Toast.LENGTH_SHORT).show();
            }else if (str.equals("1")){
                Toast.makeText(getApplicationContext(),"密码修改成功",Toast.LENGTH_SHORT).show();
                finish();
            }else if (str.equals("2")){
                Toast.makeText(getApplicationContext(),"修改失败",Toast.LENGTH_SHORT).show();
            }else if (str.equals("3")){
                Toast.makeText(getApplicationContext(),"旧密码不正确",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting_pwdedit);

        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");
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
        getEtIsNewPwd = (EditText)findViewById(R.id.PwdEdit_Et_isnewpwd);
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
                    if (getmEtNewpwd.getText().toString()
                            .equals(getEtIsNewPwd.getText().toString())){
                        getEtIsNewPwd.setError(null);
                        Thread th = new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                setUserPassword();

                                Message m = new Message();
                                h.sendMessage(m);
                            }
                        };
                        th.start();
                    }else{
                        getEtIsNewPwd.setError("确认密码与密码不一致");
//                            mUserCpassword.setText("");
                    }
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
//                           Toast.makeText(Personal_setting_PwdEditActivity.this,"密码修改成功！",Toast.LENGTH_SHORT).show();
//                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void setUserPassword() {
        try {
            str = "";
            URI u = new URI(Urls.urlSetUserPassword);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("userId", userId);
            NameValuePair pair2 = new BasicNameValuePair("userPassword",mEtOldpwd.getText().toString());
            NameValuePair pair3 = new BasicNameValuePair("userNpassword",getmEtNewpwd.getText().toString());
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);
            pairs.add(pair2);
            pairs.add(pair3);

            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity httpentity = response.getEntity();
            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = "";

                while ((string = buffer.readLine()) != null) {
                    str += string;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
