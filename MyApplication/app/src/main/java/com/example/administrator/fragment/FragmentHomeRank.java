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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.adapter.AdapterRanklist;
import com.example.administrator.domain.DataRanklist;
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

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;

/**
 * Created by Administrator on 2016/11/22.
 * 排行榜fragment
 */
public class FragmentHomeRank extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private ArrayAdapter<String> adapter;
    private List<DataRanklist> lrl = new ArrayList<>();
    private ListView lv_ranklist;
    private AdapterRanklist adapter_ranklist;
    private boolean isFirst = true;
    private View view;
    private TextView Tvname;
    private BGARefreshLayout mRefreshLayout;
    private String str;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ranklist, container, false);
        lv_ranklist = (ListView) view.findViewById(R.id.Lv_ranklist);

        initRefreshLayout();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isFirst){
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

        lv_ranklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //美食名称
                Tvname = (TextView)view.findViewById(R.id.Tv_ranklist_recipename);
                Intent intent = new Intent(getActivity(), RecipeShowActivity.class);
                intent.putExtra("Id",lrl.get(i).getRecipesId());
                startActivity(intent);
            }
        });
    }

    private void getHttpData() {
        str = "";
        try {
            URI u = new URI(Urls.urlRanklist);

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u);

            HttpResponse response = httpclient.execute(httppost);

            HttpEntity httpentity = response.getEntity();

            if (httpentity != null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(httpentity.getContent()));
                String string="";

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
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setParse();

            adapter_ranklist = new AdapterRanklist(lrl, getActivity());
            lv_ranklist.setAdapter(adapter_ranklist);
        }
    };

    private void setParse() {
        if (str == "") {
            Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject json = new JSONObject(str);

                JSONArray jsonArray = json.getJSONArray("Ranklist");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DataRanklist data = new DataRanklist();
                    data.setId(jsonObject.getInt("recipesId"));
                    data.setName(jsonObject.getString("recipesName"));
                    data.setImage(jsonObject.getString("recipesImage"));
                    data.setNum(jsonObject.getInt("recipesCollect"));
                    data.setRecipesId(jsonObject.getString("recipesId"));
                    data.setRank(i+1);

                    lrl.add(data);
                }
                str = null;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initRefreshLayout() {
        mRefreshLayout=(BGARefreshLayout) view.findViewById(R.id.Rl_ranklist_listview_refresh);
        // 为BGARefreshLayout设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder = new BGAStickinessRefreshViewHolder(getActivity(),true);
        stickinessRefreshViewHolder.setStickinessColor(R.color.yellow);
        stickinessRefreshViewHolder.setRotateImage(R.drawable.icon_refresh_stickiness);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(stickinessRefreshViewHolder);
    }
    /**
     * 下拉刷新
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        getHttpData();
                        Message m1 = new Message();
                        refreshHandler.sendMessage(m1);
                    }
                }.start();
            }
        }, 1000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    private Handler refreshHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            lrl.clear();
            setParse();
            adapter_ranklist.notifyDataSetChanged();
            mRefreshLayout.endRefreshing();
            Toast.makeText(getActivity(),"刷新成功",Toast.LENGTH_SHORT).show();
        }
    };

}

