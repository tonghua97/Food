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

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;

/**
 * Created by Administrator on 2016/11/22.
 * 推荐fragment
 */
public class FragmentHomeRecommend extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{

    private View view;
    private List<DataRecommend> ls = new ArrayList<DataRecommend>();
    private AdapterRecommend recommednAdapter;
    private BGARefreshLayout mRefreshLayout;
    private ListView lv;
    private boolean isFirst = true;
    private TextView Tvname;
    private String str;
    private int totalPage = 1;
    private int mPage = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);

        lv = (ListView) view.findViewById(R.id.Lv_recommend);
        initRefreshLayout();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        str = "";
        try {
            URI u = new URI(Urls.urlCommend+"?num="+ mPage);
            Log.e("getHttpData: ", Urls.urlCommend+"?num="+ mPage);
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

            recommednAdapter = new AdapterRecommend(getActivity(), ls);
            lv.setAdapter(recommednAdapter);
        }
    };

    private void setParse() {
        if (str == "") {
            Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject json = new JSONObject(str);
                totalPage = json.getInt("totalPage");

                JSONArray jsonArray = json.getJSONArray("Recipes");

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
                str = null;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 配置BGARefreshLayout
     */
    private void initRefreshLayout() {
        mRefreshLayout=(BGARefreshLayout) view.findViewById(R.id.Rl_recommend_listview_refresh);
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
                        m2.arg1 = lv.getLastVisiblePosition();
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
            ls.clear();
            setParse();
            recommednAdapter.notifyDataSetChanged();
            mRefreshLayout.endRefreshing();
            Toast.makeText(getActivity(),"刷新成功",Toast.LENGTH_SHORT).show();
        }
    };

    private Handler moreDataHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setParse();
            //不重新设置setAdapter，使用notifyDataSetChanged()，可以让页面不跳转
            //recommednAdapter = new AdapterRecommend(getActivity(), ls);
            //lv.setAdapter(recommednAdapter);
            recommednAdapter.notifyDataSetChanged();
            //int count = msg.arg1;
            //lv.setSelection(count);
            mRefreshLayout.endLoadingMore();
            Toast.makeText(getActivity(),"加载成功",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.beginRefreshing();
    }
}
