package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterSearchGirdView;
import com.example.administrator.adapter.AdapterSearchListView;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    private ListView mListView;
    private GridView mGirdView;
    private List<String> list = new ArrayList<>();
    private List<String> listGrid = new ArrayList<>();
    private AdapterSearchGirdView adapter;
    private AdapterSearchListView adapter2;
    private EditText mEt;
    private String array[];
    private String str;
    private ImageView mSearch;


    private Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setParse();

            adapter2 = new AdapterSearchListView(getApplicationContext(),list);
            mListView.setAdapter(adapter2);
            mListView.deferNotifyDataSetChanged();
        }
    };

    private void setParse() {
        if (str == ""){
            return;
        }else{
            str = str.substring(str.indexOf("["), str.length());

            try {
                JSONArray jsonArray = new JSONArray(str);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String string = jsonObject.getString("recipesName");
                    list.add(string);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getViews();
        adapter2 = new AdapterSearchListView(this,list);
        mListView.setAdapter(adapter2);

        Intent intent = getIntent();
        String recipesName = intent.getStringExtra("recipesName");
        if (recipesName != null){
            mEt.setText(recipesName);
            mEt.setFocusable(true);
//            list.clear();
//            new Thread(){
//                @Override
//                public void run() {
//                    super.run();
//                    getHttpRecipesName();
//
//                    Message m = new Message();
//                    h.sendMessage(m);
//                }
//            }.start();
        }

        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.clear();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        getHttpRecipesName();

                        Message m = new Message();
                        h.sendMessage(m);
                    }
                }.start();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView Tv = (TextView) view.findViewById(R.id.search_listview_Tv_name);
                mEt.setText(Tv.getText().toString());
                Intent intent = new Intent(SearchActivity.this,SearchListActivity.class);
                intent.putExtra("title",Tv.getText().toString());
                intent.putExtra("Url",Urls.urlSearchShow);
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,SearchListActivity.class);
                intent.putExtra("title",mEt.getText().toString());
                intent.putExtra("Url",Urls.urlSearchShow);
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });

    }

    public void getHttpRecipesName() {
        str = "";
        try {
            URI u = new URI(Urls.urlSearch);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(u);

            NameValuePair pair = new BasicNameValuePair("recipesName",mEt.getText().toString());
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
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getViews() {
        mListView = (ListView)findViewById(R.id.search_list);
        mEt = (EditText)findViewById(R.id.search_Et);
        mSearch = (ImageView)findViewById(R.id.search_sousuo);
    }

}
