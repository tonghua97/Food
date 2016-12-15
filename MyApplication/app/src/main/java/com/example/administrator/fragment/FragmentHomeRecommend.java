package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterRecommend;
import com.example.administrator.domain.DataRecommend;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.RecipeShowActivity;
import com.example.administrator.ui.Urls;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
 * Created by Administrator on 2016/11/22.
 * 推荐fragment
 */
public class FragmentHomeRecommend extends Fragment {

    private View view;
    private List<DataRecommend> ls = new ArrayList<DataRecommend>();
    private AdapterRecommend recommednAdapter;
    private ListView lv;
    private boolean isFirst = true;
    private TextView Tvname;
    private String str;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setParse();

            recommednAdapter = new AdapterRecommend(getActivity(), ls);
            lv.setAdapter(recommednAdapter);
        }
    };

    private void setParse() {
        if (str == "") {
            return;
        } else {
            str = str.substring(str.indexOf("["), str.length());

            try {
                JSONArray jsonArray = new JSONArray(str);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DataRecommend data = new DataRecommend();
                    data.setName(jsonObject.getString("recipesName"));
                    data.setId(jsonObject.getLong("commendId"));
                    data.setIntroduction(jsonObject.getString("recipesIntro"));
                    data.setImage(jsonObject.getString("recipesImage"));
                    data.setRecipesId(jsonObject.getString("recipesId"));

                    ls.add(data);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        lv = (ListView) view.findViewById(R.id.Lv_recommend);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        if (isFirst){
//            getData();
//
//            isFirst = false;
//        }

        if (isFirst) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    getHttpData();

                    Message m = new Message();
                    handler.sendMessage(m);
                }
            }.start();

            isFirst = false;
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), RecipeShowActivity.class);
                Tvname = (TextView) view.findViewById(R.id.Tv_recommend_name);
                intent.putExtra("NAME", Tvname.getText().toString());
                intent.putExtra("Id",ls.get(i).getRecipesId());
                startActivity(intent);
            }
        });
    }


    public void getHttpData() {
        try {
            URI u = new URI(Urls.urlCommend);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u);

            HttpResponse response = httpclient.execute(httppost);

            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string = null;

                while ((string = buffer.readLine()) != null) {
                    str += string;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
