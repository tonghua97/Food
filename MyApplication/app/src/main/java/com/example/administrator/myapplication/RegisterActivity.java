package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ui.Urls;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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
 * 注册页
 */

public class RegisterActivity extends Activity {

    private TextView mTvBack;
    private Button mRegister;
    private EditText mUserAccount;
    private EditText mUserPassword;
    private EditText mUserCpassword;
    private EditText mUserName;
    private EditText mUserNum;
    private EditText mUserPost;
    private String str;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1){
                if (str.equals("0")){
                    mUserAccount.setError("该账号已存在");
                }else{
                    mUserAccount.setError(null);
                }
//                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 2){
                if (str.equals("0")){
                    Toast.makeText(getApplicationContext(),"账号已存在，请重新输入",Toast.LENGTH_SHORT).show();
                }else if (str.equals("1")){
                    Toast.makeText(getApplicationContext(),"手机号已经使用过，请输入新个手机号",Toast.LENGTH_SHORT).show();
                }else if (str.equals("2")){
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginVerifyActivity.class);
                    startActivity(intent);
                    finish();
                }else if (str.equals("3")){
                    Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

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
        mUserAccount.setOnFocusChangeListener(mFocusListener);
        mUserCpassword.setOnFocusChangeListener(mFocusListener);
    }

    public void getViews() {
        //返回
        mTvBack = (TextView)findViewById(R.id.register_Tv_back);
        //注册
        mRegister = (Button)findViewById(R.id.register_Btn_register);
        //用户账号
        mUserAccount = (EditText)findViewById(R.id.register_userAccount);
        //用户密码
        mUserPassword = (EditText)findViewById(R.id.register_userPassword);
        //确认密码
        mUserCpassword = (EditText)findViewById(R.id.register_userCpassword);
        //昵称
        mUserName = (EditText)findViewById(R.id.register_userName);
        //手机号
        mUserNum = (EditText)findViewById(R.id.register_userNum);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.register_Tv_back:
                    finish();
                    break;
                case R.id.register_Btn_register:
                    Thread t = new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            getHttpRegister();

                            Message m = new Message();
                            handler.sendEmptyMessage(2);
                        }
                    };
                    t.start();
//                    Intent intent = new Intent(RegisterActivity.this,LoginVerifyActivity.class);
//                    startActivity(intent);
//                    finish();
                    break;
            }

        }
    };

    View.OnFocusChangeListener mFocusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId()){
                case R.id.register_userAccount:
                    if (b){

                    }else{
                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                getHttpUserAccount();

                                Message m = new Message();
                                handler.sendEmptyMessage(1);
                            }
                        };
                        thread.start();
                    }
                    break;
                case R.id.register_userCpassword:
                    if (b){

                    }else{
                        if (mUserPassword.getText().toString()
                                .equals(mUserCpassword.getText().toString())){
                            mUserCpassword.setError(null);
                        }else{
                            mUserCpassword.setError("确认密码与密码不一致");
//                            mUserCpassword.setText("");
                        }
                    }
                    break;
            }
        }
    };

    public void getHttpUserAccount() {
        try {
            str = "";
            URI u = new URI(Urls.urlIsUserAccount);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u);

            NameValuePair pair = new BasicNameValuePair("userAccount",mUserAccount.getText().toString());
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair);

            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity httpentity = response.getEntity();
            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = null;

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

    public void getHttpRegister() {
        try {
            str = "";
            URI u = new URI(Urls.urlRegister);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("userAccount",mUserAccount.getText().toString());
            NameValuePair pair2 = new BasicNameValuePair("userPassword",mUserPassword.getText().toString());
            NameValuePair pair3 = new BasicNameValuePair("userName",mUserName.getText().toString());
            NameValuePair pair4 = new BasicNameValuePair("userNum",mUserNum.getText().toString());

            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);
            pairs.add(pair2);
            pairs.add(pair3);
            pairs.add(pair4);

            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);

            HttpEntity httpentity = response.getEntity();
            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = null;

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
