package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.domain.DataUser;
import com.example.administrator.myapplication.CollectionActivity;
import com.example.administrator.myapplication.LoginVerifyActivity;
import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.PersonalAboutActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.RegisterActivity;
import com.example.administrator.myapplication.SetActivity;
import com.example.administrator.ui.CircleImageView;
import com.example.administrator.ui.Urls;
import com.example.administrator.ui.Utils;
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
 * Created by Administrator on 2016/11/22.
 * 个人中心fragment
 */
public class FragmentPerson extends Fragment{
    private View view;
    private RelativeLayout title1;
    private RelativeLayout title2;
    private RelativeLayout title3;
    private RelativeLayout title4;
    private Button mLogin;
    private Button mRegister;
    private RelativeLayout mRlaymiddle;
    private LinearLayout mUsername;
    private LinearLayout mLlaylogin;
    private LinearLayout mLlayBottom;
    private TextView mLlayname;
    private Button mExitLogin;
    private CircleImageView head;
    private String str;
    private String userId;
    private DataUser data;
    private String Id;
    private String userName;
    private String urlImage;
    public ImageLoader imageLoader = ImageLoader.getInstance();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (str.equals("0")){
                Toast.makeText(getActivity(),"错误",Toast.LENGTH_SHORT).show();
            }else{
                setParse();
                setViews();
            }

        }
    };

    private void setViews() {
        mLlayname.setText(data.getUserName());

        urlImage = data.getUserImage();
        if (urlImage.contains("http://")){
            String string = urlImage.substring(7, urlImage.indexOf("/", 7));
            urlImage = urlImage.replaceAll(string, Urls.mIp);
            Log.e(urlImage,"String");

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.img_loading)  //设置图片在下载期间显示的图片
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                    .build();//构建完成
            imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
            imageLoader.displayImage(urlImage,head,options);
        }else {
            head.setImageResource(R.drawable.head);
        }

    }

    private void setParse() {
        if (str == ""){
            return;
        }else{

            try {
                JSONObject json = new JSONObject(str);
                data = new DataUser();
                data.setUserName(json.getString("userName"));
                data.setUserImage(json.getString("userImage"));
                data.setUserAccount(json.getString("userAccount"));
                data.setUserNum(json.getString("userNum"));
                data.setUserPost(json.getString("userPost"));
                data.setUserSex(json.getString("userSex"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_personal,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getViews();
//        Intent intent = getActivity().getIntent();
//        String userName = intent.getStringExtra("userName");
//        Utils.username = intent.getStringExtra("userName");
        SharedPreferences spf = getActivity().getSharedPreferences("MYAPP",Context.MODE_PRIVATE);
        Id = spf.getString("userId","");
        userName = spf.getString("userName","");

        if (Id != ""){
            Utils.isTrue = 2;
        }

        if (Utils.isTrue == 2){
            mRlaymiddle.setVisibility(View.GONE);
            mUsername.setVisibility(View.VISIBLE);
            mLlaylogin.setVisibility(View.VISIBLE);
            mLlayBottom.setVisibility(View.GONE);
//            mLlayname.setText(userName);
            setdata();
        }else {
            mRlaymiddle.setVisibility(View.VISIBLE);
            mUsername.setVisibility(View.GONE);
            mLlaylogin.setVisibility(View.GONE);
            mLlayBottom.setVisibility(View.VISIBLE);
        }

        setListener();

    }

    private void setListener() {
        title1.setOnClickListener(myListener);
        title2.setOnClickListener(myListener);
        title3.setOnClickListener(myListener);
        title4.setOnClickListener(myListener);
        mLogin.setOnClickListener(myListener);
        mRegister.setOnClickListener(myListener);
        mExitLogin.setOnClickListener(myListener);
        head.setOnClickListener(myListener);
    }

    public void getViews() {
        //头像
        head = (CircleImageView)view.findViewById(R.id.personal_iv_head);
        //收藏
        title1 = (RelativeLayout)view.findViewById(R.id.personal_rl_title1);
        //设置
        title2 = (RelativeLayout)view.findViewById(R.id.personal_rl_title2);
        //关于
        title3 = (RelativeLayout)view.findViewById(R.id.personal_rl_title3);
        //反馈
        title4 = (RelativeLayout)view.findViewById(R.id.personal_rl_title4);
        //登录
        mLogin = (Button)view.findViewById(R.id.personal_btn_login);
        //注册
        mRegister = (Button)view.findViewById(R.id.personal_btn_register);
        //登录和注册view
        mRlaymiddle = (RelativeLayout)view.findViewById(R.id.personal_rl_middle);
        //用户名view
        mUsername = (LinearLayout)view.findViewById(R.id.personal_Llay_username);
        //personal_Llay_login显示退出登录
        mLlaylogin = (LinearLayout)view.findViewById(R.id.personal_Llay_login);
        //退出登录
        mExitLogin = (Button)view.findViewById(R.id.personal_Btn_exitLogin);
        //personal_Llay_bottom
        mLlayBottom = (LinearLayout)view.findViewById(R.id.personal_Llay_bottom);
        //用户名
        mLlayname = (TextView)view.findViewById(R.id.personal_Llay_Tv_username);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.personal_iv_head:
                    Intent intent = new Intent(getActivity(),SetActivity.class);
                    startActivity(intent);
                    break;
                case R.id.personal_rl_title1:
                    Intent intent1 = new Intent(getActivity(), CollectionActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.personal_rl_title2:
                    Intent intent2 = new Intent(getActivity(), SetActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.personal_rl_title3:
                    Intent intent6 = new Intent(getActivity(), PersonalAboutActivity.class);
                    startActivity(intent6);
                    break;
                case R.id.personal_rl_title4:
                    break;
                case R.id.personal_btn_login:
                    Intent intent3 = new Intent(getActivity(), LoginVerifyActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.personal_btn_register:
                    Intent intent4 = new Intent(getActivity(), RegisterActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.personal_Btn_exitLogin:
                    SharedPreferences spf = getActivity().getSharedPreferences("MYAPP",getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                    editor.clear();
                    editor.commit();
//                    Utils.username = null;
                    Utils.isTrue = 1;
                    Intent intent5 = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent5);
//                    getActivity().finish();
                    break;
            }
        }
    };

    private void setdata() {
//        SharedPreferences spf = getActivity().getSharedPreferences("MYAPP",getActivity().MODE_PRIVATE);
//        userId = spf.getString("userId","");
//        mUsername.setText(name);

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                getHttpUser();

                Message m = new Message();
                handler.sendMessage(m);
            }
        };
        thread.start();
    }

    public void getHttpUser() {
        try {
            str = "";
            URI u = new URI(Urls.urlUser);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair = new BasicNameValuePair("userId",Id);
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair);

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

}
