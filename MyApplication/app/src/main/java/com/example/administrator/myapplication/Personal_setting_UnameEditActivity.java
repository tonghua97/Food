package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.Replaceable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

/**
 * Created by 梁爽 on 16.11.24.
 */
public class Personal_setting_UnameEditActivity extends Activity {
    private Button mBtnClear;   //清空用户名
    private Button mBtnBack;    //返回键
    private Button mBtnSave;    //保存用户名的修改
    private EditText mEtUname;  //修改用户名
    private String userName;
    private String str;
    private String userId;

    private Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (str.equals("0")){
                Toast.makeText(getApplicationContext(),"该用户不存在",Toast.LENGTH_SHORT).show();
            }else if (str.equals("1")){
                Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Personal_setting_UnameEditActivity.this,SetActivity.class);
                startActivity(intent);
                finish();
            }else if (str.equals("2")){
                Toast.makeText(getApplicationContext(),"修改失败",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting_unameedit);

        //获取控件
        getViews();

        Intent intent = getIntent();
        userName = intent.getStringExtra("setting_userName");
        mEtUname.setText(userName);

        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");

        //设置监听
        setListener();

        //保存用户名的修改
//        setUname();
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
                /*返回键效果*/
                case R.id.UnameEdit_Bt_Back:
                    finish();
                    break;

                /*保存用户名的修改*/
                case R.id.UnameEdit_Bt_Save:
                    Thread t = new Thread(){
                        @Override
                        public void run() {
                            super.run();

                            UpdateUserName();
                            Message m = new Message();
                            h.sendMessage(m);
                        }
                    };
                    t.start();
//                    SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = spf.edit();
//                    editor.putString("UNAME",mEtUname.getText().toString());
//                    editor.commit();
//                    Toast.makeText(Personal_setting_UnameEditActivity.this,"用户名修改并保存成功！",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Personal_setting_UnameEditActivity.this,SetActivity.class);
//                    startActivity(intent);
                    break;

                /*清空用户名EditText*/
                case R.id.UnameEdit_btn_clear:
                    mEtUname.setText("");
                    break;
            }
        }
    }

    private void UpdateUserName() {
        try {
            str = "";
            URI u = new URI(Urls.urlSetUserName);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("userId",userId);
            NameValuePair pair2 = new BasicNameValuePair("userName",mEtUname.getText().toString());
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

//    /**
//     * 保存用户名的修改
//     */
//    private void setUname(){
//        SharedPreferences spf = getSharedPreferences("UNAME_EDIT",Context.MODE_PRIVATE);
//        String Uname = spf.getString("UNAME","");
//
//        mEtUname.setText(Uname);
//    }
}
