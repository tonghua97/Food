package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
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

/**
 * Created by 刘博园 on 16.12.24.
 */
public class Personal_setting_EmailActivity extends Activity {
    private Button mBtnBack;
    private Button mBtnSave;
    private EditText mEtEmail;
    private String mEmail;
    private String userId;
    private String str;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (str.equals("1")){
                Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"修改失败",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting_mail);

        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");

        //获取控件
        getViews();

        Intent intent = getIntent();
        mEmail = intent.getStringExtra("setting_email");
        mEtEmail.setText(mEmail);
        //设置监听
        setListener();
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
                    Thread t = new Thread(){
                        @Override
                        public void run() {
                            super.run();

                            UpdateUserPost();
                            Message m = new Message();
                            handler.sendMessage(m);
                        }
                    };
                    t.start();
                    break;
                default:
                    break;
            }
        }

        private void UpdateUserPost() {
            try {
                str = "";
                URI u = new URI(Urls.urlSetUserPost);
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(u);

                NameValuePair pair1 = new BasicNameValuePair("userId",userId);
                NameValuePair pair2 = new BasicNameValuePair("userPost",mEtEmail.getText().toString());
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
    }


}
