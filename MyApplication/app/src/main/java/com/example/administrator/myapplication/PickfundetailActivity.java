package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.domain.DataPickfun;
import com.example.administrator.ui.Urls;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
 * Created by 姜佳妮 on 2016/11/24.
 * 拾趣详情页
 */
public class PickfundetailActivity extends Activity {
    private TextView tvtitle,tvcontent;         //拾趣文章标题,文章内容
    private Button btnback;                  //返回按钮
    private ImageView Imgrecipe,collect;    //文章图片、收藏
    private boolean isCollect = true;
    private String userId;
    private String str;
    private String funId;
    public ImageLoader imageLoader = ImageLoader.getInstance();
    private DataPickfun data;
    private String urlImage;
    private ProgressDialog dialog;

    private Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0){
                setParse();
                setViews();
                dialog.dismiss();
            }else if (msg.what == 1){
                if(msg.arg1 == 1){
                    Toast.makeText(getApplicationContext(),"收藏成功",Toast.LENGTH_SHORT).show();
                    collect.setImageResource(R.drawable.img_collect);
                    isCollect = true;
                }else {
                    Toast.makeText(getApplicationContext(),"已收藏",Toast.LENGTH_SHORT).show();
                }
            }else if (msg.what == 2) {
                if(msg.arg1 ==1){
                    Toast.makeText(getApplicationContext(),"取消成功",Toast.LENGTH_SHORT).show();
                    collect.setImageResource(R.drawable.img_collect_unclick);
                    isCollect = false;
                }
            }
        }
    };

    private void setParse() {
        if (str == ""){
            return;
        }else{

            try {
                JSONObject json = new JSONObject(str);
                String IsCollect = json.getString("IsCollect");
                JSONArray jsonArray = json.getJSONArray("Fun");

                JSONObject jsonObject = jsonArray.getJSONObject(0);
                data = new DataPickfun();
                data.setUrl(jsonObject.getString("funImage"));
                data.setmPickfun_Name(jsonObject.getString("funTitle"));
                data.setId(jsonObject.getInt("funId"));
                data.setTvcontent(jsonObject.getString("funContent"));
                if (IsCollect.equals("true")){
                    isCollect = true;
                }else {
                    isCollect = false;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickfundetail);

        //获取控件
        getViews();
        //获取美食名称
        Intent intent = getIntent();
        funId = intent.getStringExtra("FUNID");
        //设置监听
        setListener();
    }

    private void setViews() {
        tvtitle.setText(data.getmPickfun_Name());
        tvcontent.setText(data.getTvcontent());

        urlImage = data.getUrl();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        imageLoader.displayImage(urlImage,Imgrecipe,options);
        if(isCollect){
            collect.setImageResource(R.drawable.img_collect);
        }else {
            collect.setImageResource(R.drawable.img_collect_unclick);
        }
    }

    private void setListener() {
        btnback.setOnClickListener(myListener);
        collect.setOnClickListener(myListener);
        Imgrecipe.setOnClickListener(myListener);
    }

    public void getViews() {
        tvtitle = (TextView) findViewById(R.id.Tv_pickfundetail_title);
        btnback = (Button) findViewById(R.id.Btn_pickfundetail_back);
        Imgrecipe = (ImageView) findViewById(R.id.Iv_pickfundetail_recipeimg);
        tvcontent = (TextView) findViewById(R.id.Tv_pickfundetail_content);
        collect = (ImageView) findViewById(R.id.Iv_pickfundetail_collect);
        dialog = new ProgressDialog(PickfundetailActivity.this);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Btn_pickfundetail_back:
                    finish();
                    break;
                case R.id.Iv_pickfundetail_collect:
                    if (!isCollect){
                        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
                        userId = spf.getString("userId","");
                        if (userId == ""){
                            Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PickfundetailActivity.this,LoginVerifyActivity.class);
                            startActivity(intent);
                        }else {
                            Thread thread = new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    String result = setFunCollect("add");
                                    Message m = new Message();
                                    m.what = 1;
                                    if(result.equals("success")) {
                                        m.arg1 = 1;
                                    }else {
                                        m.arg1 = 2;
                                    }
                                    h.sendMessage(m);
                                }
                            };
                            thread.start();
                        }

                    }else {
                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                String result = setFunCollect("delete");
                                Message m = new Message();
                                m.what = 2;
                                if(result.equals("success")) {
                                    m.arg1 = 1;
                                }else {
                                    m.arg1 = 2;
                                }
                                h.sendMessage(m);
                            }
                        };
                        thread.start();
                    }
                    break;
            }
        }
    };

    public void getHttpData() {
        str = "";
        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        userId = spf.getString("userId","");
        try {
            URI u = new URI(Urls.urlFun);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("funId",funId);
            NameValuePair pair2 = new BasicNameValuePair("userId",userId);
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);
            pairs.add(pair2);
            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = null;

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

    public String setFunCollect(String Operate) {
        String result = "";
        str = "";
        try {
            URI u = new URI(Urls.urlSetFunCollect);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair1 = new BasicNameValuePair("userId",userId);
            NameValuePair pair2 = new BasicNameValuePair("funId",funId);
            NameValuePair pair3 = new BasicNameValuePair("operate",Operate);
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair1);
            pairs.add(pair2);
            pairs.add(pair3);

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
            JSONObject json = new JSONObject(str);
            result = json.getString("msg");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        dialog.setTitle("提示信息");
        dialog.setMessage("数据加载中......");
        dialog.show();
        final Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                getHttpData();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message m = new Message();
                h.sendEmptyMessage(0);
            }
        };
        thread.start();
    }
}

