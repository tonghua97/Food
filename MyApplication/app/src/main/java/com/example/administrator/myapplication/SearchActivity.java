package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {
    private String uri = "http://10.7.92.81/http/getRecipesName2";
    private MultiAutoCompleteTextView searchTv;
    private List<String> list = new ArrayList<>();
    private String array[];
    private String str;

//    private Handler h = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            setParse();
//        }
//    };

//    private void setParse() {
//        str = str.substring(str.indexOf("["), str.length());
//
//        try {
//            JSONArray jsonArray = new JSONArray(str);
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String string = jsonObject.getString("recipesName");
//                list.add(string);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchTv = (MultiAutoCompleteTextView)findViewById(R.id.search_Tv_edit);
//
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                getHttpRecipesName();
//
//                Message m = new Message();
//                h.sendMessage(m);
//            }
//        }.start();
//        for (int i = 0;i < list.size(); i++){
//            array[i] = list.get(i);
//        }
        String array[] = {"limei", "wangmei", "liming", "jianglan",
                "huangbei", "lihong", "liwen"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        searchTv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        searchTv.setAdapter(adapter);
    }

    public void getHttpRecipesName() {
        try {
            URI u = new URI(uri);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);
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
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
