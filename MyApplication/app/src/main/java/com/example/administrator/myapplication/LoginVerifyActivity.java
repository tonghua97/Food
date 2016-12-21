package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.administrator.ui.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录页
 */

public class LoginVerifyActivity extends Activity {

    private TextView mTvBack;
    private Button mLogin;
    private EditText mPassword;
    private TextView mRegister;
    private String str;
    private EditText mUseraccount;
    private String userId;
    private String userName;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (str.equals("0")){
                Toast.makeText(getApplicationContext(),"账号不存在",Toast.LENGTH_SHORT).show();
            }else if (str.equals("2")){
                Toast.makeText(getApplicationContext(),"密码不正确",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                setParse();
                SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.putString("userId",userId);
                editor.commit();
                Utils.username = userName;
                Utils.utils = 3;
                Utils.isTrue = 2;
                Intent intent = new Intent(LoginVerifyActivity.this,MainActivity.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        }
    };

    private void setParse() {
        if (str == ""){
            return;
        }else{
            try {
                JSONObject json = new JSONObject(str);
                userId = json.getString("userId");
                userName = json.getString("userName");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verify);

        //获取控件
        getViews();
        //设置监听
        setListener();
    }

    private void setListener() {
        mTvBack.setOnClickListener(myListener);
        mLogin.setOnClickListener(myListener);
        mRegister.setOnClickListener(myListener);
    }

    public void getViews() {
        //返回
        mTvBack = (TextView)findViewById(R.id.login_verify_Tv_back);
        //登录
        mLogin = (Button)findViewById(R.id.login_verify_Btn_login);
        //用户名
        mUseraccount = (EditText)findViewById(R.id.login_verify_Et_username);
        //密码
        mPassword = (EditText)findViewById(R.id.login_verify_Et_password);
        //注册
        mRegister = (TextView)findViewById(R.id.login_verify_Tv_register);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_verify_Tv_back:
                    finish();
                    break;
                case R.id.login_verify_Btn_login:
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            getHttpLogin();

                            Message m = new Message();
                            handler.sendMessage(m);
                        }
                    };
                    thread.start();
//                    SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = spf.edit();
//                    editor.putString("username",mUseraccount.getText().toString());
//                    editor.commit();
//                    Utils.username = mUseraccount.getText().toString();
//                    Utils.utils = 3;
//                    Utils.isTrue = 2;
//                    Intent intent = new Intent(LoginVerifyActivity.this,MainActivity.class);
//                    startActivity(intent);
                    break;
                case R.id.login_verify_Tv_register:
                    Intent intent2 = new Intent(LoginVerifyActivity.this,RegisterActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
            }
        }
    };

    public void getHttpLogin() {
        try {
            str = "";
            URI u = new URI(Urls.urlIsLogin);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("userAccount",mUseraccount.getText().toString());
            NameValuePair pair2 = new BasicNameValuePair("userPassword",mPassword.getText().toString());
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);
            pairs.add(pair2);

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
