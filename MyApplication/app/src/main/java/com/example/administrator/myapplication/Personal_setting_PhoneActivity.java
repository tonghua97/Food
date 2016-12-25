package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
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
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by 刘博园 on 16.12.14.
 */
public class Personal_setting_PhoneActivity extends Activity {
    private Button mBtnBack;
    private Button mBtnGetCord;
    private Button mBtnSaveCord;
    private EditText mEtPhone;
    private EditText mEtCord;
    private String iPhone;
    private String iCord;
    private int time = 60;
    private boolean flag = true;
    private TextView point;
    private String str;
    private String userId;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting_phone);

        //获取控件
        getViews();

        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");
        Intent i = getIntent();
        mPhone = i.getStringExtra("setting_phone");
        mEtPhone.setText(mPhone);

        //设置监听
        setListener();
        //保存手机号的修改
        setPhone();
//        SMSSDK.initSDK(this, "1904c31d29a41", "e73946a5b939c0626b3c83b92fea9c0d");
        SMSSDK.initSDK(this, "19da0787f3f9c", "025d51ab69bbc393f1237b78301872d7");
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
    }
    /**
     * 获取控件
     */
    private void getViews(){
        mBtnBack = (Button)findViewById(R.id.Phone_Bt_Back);
        mBtnGetCord = (Button)findViewById(R.id.Bt_getcord);
        mBtnSaveCord = (Button)findViewById(R.id.Bt_saveCord);
        mEtPhone = (EditText)findViewById(R.id.Et_phone);
        mEtCord = (EditText)findViewById(R.id.Et_cord);
        point = (TextView) findViewById(R.id.point);
    }
    /**
     * 设置监听
     */
    private void setListener(){
        MyListener listener = new MyListener();
        mBtnBack.setOnClickListener(listener);
        mBtnGetCord.setOnClickListener(listener);
        mBtnSaveCord.setOnClickListener(listener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //返回键
                case R.id.Phone_Bt_Back:
                    finish();
                    break;
                case R.id.Bt_getcord:
                    if(!TextUtils.isEmpty(mEtPhone.getText().toString().trim())){
                        if(mEtPhone.getText().toString().trim().length()==11){
                            iPhone = mEtPhone.getText().toString().trim();
                            SMSSDK.getVerificationCode("86",iPhone);
                            mEtCord.requestFocus();
                            mBtnGetCord.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(Personal_setting_PhoneActivity.this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
                            mEtPhone.requestFocus();
                        }
                    }else{
                        Toast.makeText(Personal_setting_PhoneActivity.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
                        mEtPhone.requestFocus();
                    }
                    break;
                case R.id.Bt_saveCord:
                    if(!TextUtils.isEmpty(mEtCord.getText().toString().trim())){
                        if(mEtCord.getText().toString().trim().length()==4){
                            iCord = mEtCord.getText().toString().trim();
                            SMSSDK.submitVerificationCode("86", iPhone, iCord);
                            flag = false;
                        }else{
                            Toast.makeText(Personal_setting_PhoneActivity.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
                            mEtCord.requestFocus();
                        }
                    }else{
                        Toast.makeText(Personal_setting_PhoneActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                        mEtCord.requestFocus();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 保存手机号的修改
     */
    private void setPhone(){
        SharedPreferences spf = getSharedPreferences("PHONE_EDIT",Context.MODE_PRIVATE);
        String Uname = spf.getString("PHONE","");
        mEtPhone.setText(Uname);
    }
    //验证码送成功后提示文字
    private void reminderText() {
        point.setVisibility(View.VISIBLE);
        handlerText.sendEmptyMessageDelayed(1, 1000);
    }

    Handler handlerText =new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(time>0){
                    point.setText("验证码已发送"+time+"秒");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                }else{
                    point.setText("提示信息");
                    time = 60;
                    point.setVisibility(View.GONE);
                    mBtnGetCord.setVisibility(View.VISIBLE);
                }
            }else{
                mEtCord.setText("");
                point.setText("提示信息");
                time = 60;
                point.setVisibility(View.GONE);
                mBtnGetCord.setVisibility(View.VISIBLE);

                if (str.equals("1")){
                    Toast.makeText(Personal_setting_PhoneActivity.this,"手机号修改并保存成功！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Personal_setting_PhoneActivity.this,SetActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Personal_setting_PhoneActivity.this,"手机号修改失败！",Toast.LENGTH_SHORT).show();
                }
            }
        };
    };

    Handler handler=new Handler(){



        @Override

        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event="+event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功,验证通过
                    Toast.makeText(getApplicationContext(), "验证码校验成功", Toast.LENGTH_SHORT).show();
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            super.run();

                            UpdateUserPhone();
                            Message m = new Message();
                            handlerText.sendEmptyMessage(2);
                        }
                    };
                    thread.start();

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){//服务器验证码发送成功
                    reminderText();
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(flag){
                    mBtnGetCord.setVisibility(View.VISIBLE);
                    Toast.makeText(Personal_setting_PhoneActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    mEtPhone.requestFocus();
                }else{
                    ((Throwable) data).printStackTrace();
                    Toast.makeText(Personal_setting_PhoneActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    mEtCord.selectAll();
                }
            }
        }

    };

    private void UpdateUserPhone() {
        try {
            str = "";
            URI u = new URI(Urls.urlSetUserPhone);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("userId",userId);
            NameValuePair pair2 = new BasicNameValuePair("userNum",mEtPhone.getText().toString());
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);
            pairs.add(pair2);

            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = "";

                while ((string = buffer.readLine()) != null) {
                    str += string;
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
