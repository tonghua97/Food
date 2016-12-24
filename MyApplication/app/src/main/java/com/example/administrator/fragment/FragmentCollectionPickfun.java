package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.adapter.AdapterCollectionPickfun;
import com.example.administrator.domain.DataClassificationList;
import com.example.administrator.domain.DataCollectionPickfun;
import com.example.administrator.myapplication.PickfundetailActivity;
import com.example.administrator.myapplication.R;
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
 * Created by 刘博园on 2016/12/21.
 */

public class FragmentCollectionPickfun extends Fragment{

    private List<DataCollectionPickfun> ldcp = new ArrayList<>();
    private ListView lv_collection_pickfun;
    private AdapterCollectionPickfun collectionPickfun_adapter;
    private String str;
    private String userId;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            setParse();
            collectionPickfun_adapter = new AdapterCollectionPickfun(ldcp,getActivity());
            lv_collection_pickfun.setAdapter(collectionPickfun_adapter);
        }
    };

    private void setParse() {
        if (str == ""){
            Toast.makeText(getActivity(),"该用户没有收藏食谱",Toast.LENGTH_SHORT).show();
        }else{
//            str = str.substring(str.indexOf("["), str.length());

            try {
                JSONArray jsonArray = new JSONArray(str);

                for (int i = 0;i < jsonArray.length();i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    DataCollectionPickfun data = new DataCollectionPickfun();
                    data.setId(json.getString("funId"));
                    data.setImage(json.getString("funImage"));
                    data.setIntroduction(json.getString("funContent"));
                    data.setName(json.getString("funTitle"));
                    ldcp.add(data);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection_pickfun,container,false);

        lv_collection_pickfun = (ListView)view.findViewById(R.id.Lv_collection_pickfun);
        SharedPreferences spf = getActivity().getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        userId = spf.getString("userId","");
        getData();


        setListener();
        return view;
    }

    private void setListener() {
        lv_collection_pickfun.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),PickfundetailActivity.class);
                intent.putExtra("FUNID",ldcp.get(position).getId());
                startActivity(intent);
            }
        });
    }
//        private void getData() {
//            ldcp.add((new DataCollectionPickfun(0L,R.drawable.img_loading,"食趣","食趣")));
//            ldcp.add((new DataCollectionPickfun(1L,R.drawable.img_loading,"食趣","食趣")));
//            ldcp.add((new DataCollectionPickfun(2L,R.drawable.img_loading,"食趣","食趣")));
//            ldcp.add((new DataCollectionPickfun(3L,R.drawable.img_loading,"食趣","食趣")));
//            ldcp.add((new DataCollectionPickfun(0L,R.drawable.img_loading,"食趣","食趣")));
//            ldcp.add((new DataCollectionPickfun(1L,R.drawable.img_loading,"食趣","食趣")));
//            ldcp.add((new DataCollectionPickfun(2L,R.drawable.img_loading,"食趣","食趣")));
//            ldcp.add((new DataCollectionPickfun(3L,R.drawable.img_loading,"食趣","食趣")));
//
//        }

    private void getHttpData(){
        try {
            str = "";
            URI u = new URI(Urls.urlCollectFun);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair = new BasicNameValuePair("funcollectUser",userId);
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(pair);

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

    public void getData() {
        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();

                getHttpData();
                Message m = new Message();
                handler.sendMessage(m);
            }
        };
        t.start();
    }
}
