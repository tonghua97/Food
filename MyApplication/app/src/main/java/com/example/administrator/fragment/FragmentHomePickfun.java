package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.adapter.AdapterPickfun;
import com.example.administrator.domain.DataPickfun;
import com.example.administrator.myapplication.PickfundetailActivity;
import com.example.administrator.myapplication.R;
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
 * 拾趣fragment
 */
public class FragmentHomePickfun extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private List<DataPickfun> ldp = new ArrayList<>();
    private ListView lv_pickfun;
    private AdapterPickfun AdapterPickfun;
    private BGARefreshLayout mRefreshLayout;
    private boolean isFirst = true;
    private View view;
    private TextView TvTile;
    private String str;
    private int totalPage = 1;
    private int mPage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pickfun,container,false);
        lv_pickfun = (ListView)view.findViewById(R.id.Lv_pickfun);

        initRefreshLayout();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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

        lv_pickfun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TvTile = (TextView)view.findViewById(R.id.Tv_pickfun_title);
                Intent intent = new Intent(getActivity(), PickfundetailActivity.class);
                intent.putExtra("FUNID",ldp.get(i).getId()+"");
                startActivity(intent);
            }
        });
    }

    private void getHttpData() {
        str = "";
        try {
            URI u = new URI(Urls.urlPickfun+"?num="+ mPage);
            Log.e("getHttpData: ", Urls.urlPickfun+"?num="+ mPage);
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

            AdapterPickfun = new AdapterPickfun(ldp,getActivity());

            lv_pickfun.setAdapter(AdapterPickfun);
        }
    };

    private void setParse() {
        if (str == "") {
            Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject json = new JSONObject(str);
                totalPage = json.getInt("totalPage");

                JSONArray jsonArray = json.getJSONArray("Fun");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DataPickfun data = new DataPickfun();
                    data.setId(jsonObject.getInt("funId"));
                    data.setmPickfun_Name(jsonObject.getString("funTitle"));
                    data.setUrl(jsonObject.getString("funImage"));

                    ldp.add(data);
                }
                str = null;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initRefreshLayout() {
        mRefreshLayout=(BGARefreshLayout) view.findViewById(R.id.Rl_pickfun_listview_refresh);
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
                        mPage = 1;
                        getHttpData();
                        Message m1 = new Message();
                        refreshHandler.sendMessage(m1);
                    }
                }.start();
            }
        }, 1000);
    }

    /**
     * 上滑加载更多
     * @param refreshLayout
     * @return
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {
        mPage++;
        if(mPage > totalPage){
            mRefreshLayout.endLoadingMore();
            Toast.makeText(getActivity(),"没有更多数据了",Toast.LENGTH_SHORT).show();
            mPage--;
            return false;
        }
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        getHttpData();
                        Message m2 = new Message();
                        m2.arg1 = lv_pickfun.getLastVisiblePosition();
                        moreDataHandler.sendMessage(m2);
                    }
                }.start();
            }
        }, 1000);
        return true;
    }
    private Handler refreshHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ldp.clear();
            setParse();
            AdapterPickfun.notifyDataSetChanged();
            mRefreshLayout.endRefreshing();
            Toast.makeText(getActivity(),"刷新成功",Toast.LENGTH_SHORT).show();
        }
    };

    private Handler moreDataHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setParse();
            AdapterPickfun.notifyDataSetChanged();
            mRefreshLayout.endLoadingMore();
            Toast.makeText(getActivity(),"加载成功",Toast.LENGTH_SHORT).show();
        }
    };
}
