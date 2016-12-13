package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 * 推荐fragment
 */
public class FragmentHomeRecommend extends Fragment{

    private View view;
    private List<DataRecommend> ls = new ArrayList<DataRecommend>();
    private AdapterRecommend recommednAdapter;
    private ListView lv;
    private boolean isFirst = true;
    private TextView Tvname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        lv = (ListView)view.findViewById(R.id.Lv_recommend);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isFirst){
            getData();

            isFirst = false;
        }

        recommednAdapter = new AdapterRecommend(getActivity(),ls);
        lv.setAdapter(recommednAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), RecipeShowActivity.class);
                Tvname = (TextView)view.findViewById(R.id.Tv_recommend_name);
                intent.putExtra("NAME",Tvname.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void getData() {
        ls.add(new DataRecommend(0L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭11111111111"));
        ls.add(new DataRecommend(1L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭。"));
        ls.add(new DataRecommend(2L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭"));
        ls.add(new DataRecommend(3L,"1","茶泡饭","茶泡饭","茶泡饭茶"));
        ls.add(new DataRecommend(4L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭"));
        ls.add(new DataRecommend(5L,"1","茶泡饭","茶泡饭","茶泡饭茶"));
        ls.add(new DataRecommend(6L,"1","茶泡饭","茶泡饭","茶泡饭茶"));
        ls.add(new DataRecommend(7L,"1","茶泡饭","茶泡饭","茶泡饭茶"));
        ls.add(new DataRecommend(8L,"1","茶泡饭","茶泡饭","茶泡饭茶"));
        ls.add(new DataRecommend(9L,"1","茶泡饭","茶泡饭","茶泡饭茶"));
        ls.add(new DataRecommend(10L,"1","茶泡饭","茶泡饭","茶泡饭茶"));
        ls.add(new DataRecommend(11L,"1","茶泡饭","茶泡饭","茶泡饭茶"));
    }
}
