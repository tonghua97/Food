package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterClassificationList;
import com.example.administrator.domain.DataClassificationList;

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

public class SearchListActivity extends Activity {
    private ArrayAdapter<String> adapter;
    private List<DataClassificationList> ldc = new ArrayList<>();
    private ListView lv_classification;
    private AdapterClassificationList adapter_classification;
    private TextView mSearchEt;
    private Button BtBack;
    private String title;
    private String recipesId;
    private String str;
    private String Url;
    private String flag;
    private ImageView mBack;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            setParse();
            adapter_classification = new AdapterClassificationList(ldc,getApplication());
            lv_classification.setAdapter(adapter_classification);
        }
    };

    private void setParse() {
        if (str == ""){
            return;
        }else{
            str = str.substring(str.indexOf("["), str.length());

            try {
                JSONArray jsonArray = new JSONArray(str);

                for (int i = 0;i < jsonArray.length();i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    DataClassificationList data = new DataClassificationList();
                    data.setId(0L);
                    data.setRecipesid(json.getString("recipesId"));
                    data.setName(json.getString("recipesName"));
                    data.setImage(json.getString("recipesImage"));
                    data.setIntro(json.getString("recipesIntro"));
                    data.setNumber(json.getInt("recipesCollect"));
                    data.setTime(json.getString("recipesTime"));

                    ldc.add(data);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        //获取控件
        getViews();
        //获取点击事件的美食类型名字
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        mSearchEt.setText(title);
        Url = intent.getStringExtra("Url");
        flag = intent.getStringExtra("flag");

        final Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                getHttpData();

                Message m = new Message();
                handler.sendMessage(m);
            }
        };
        thread.start();


        lv_classification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = ldc.get(i).getRecipesid();
                Intent intent = new Intent(SearchListActivity.this,RecipeShowActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });

        mSearchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getViews() {
        lv_classification = (ListView)findViewById(R.id.Lv_searchfication);
//        mSearchEt = (EditText)findViewById(R.id.searchlist_Et);
        mSearchEt = (TextView)findViewById(R.id.searchlist_Et);
        mBack = (ImageView)findViewById(R.id.Iv_searchList_back);
    }

    public void getHttpData() {
        try {
            str = "";
            URI u = new URI(Url);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);
            NameValuePair pair;
            List<NameValuePair> pairs = null;

            if (flag.equals("1")){
                pair = new BasicNameValuePair("recipesName",title);
                pairs = new ArrayList<>();
                pairs.add(pair);
            }else if (flag.equals("2")){
                pair = new BasicNameValuePair("recipesTime",title);
                pairs = new ArrayList<>();
                pairs.add(pair);
            }else if (flag.equals("3")){
                pair = new BasicNameValuePair("recipesMfood",title);
                pairs = new ArrayList<>();
                pairs.add(pair);
            }else {
                return;
            }

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
