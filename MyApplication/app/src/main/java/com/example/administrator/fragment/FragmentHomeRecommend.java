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

import com.example.administrator.adapter.AdapterRecommend;
import com.example.administrator.domain.DataRecommend;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.RecipeShow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class FragmentHomeRecommend extends Fragment{

    private View view;
    private List<DataRecommend> ls = new ArrayList<DataRecommend>();
    private AdapterRecommend recommednAdapter;
    private ListView lv;

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
        getData();
        recommednAdapter = new AdapterRecommend(getActivity(),ls);
        lv.setAdapter(recommednAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();

                intent.setClass(getActivity(), RecipeShow.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        ls.add(new DataRecommend(0L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭11111111111"));
        ls.add(new DataRecommend(1L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭。"));
        ls.add(new DataRecommend(2L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭"));
        ls.add(new DataRecommend(3L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭"));
        ls.add(new DataRecommend(4L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭"));
    }
}
