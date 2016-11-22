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

import com.example.administrator.adapter.Adapter_Recommend;
import com.example.administrator.domain.Data_Recommend;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wcyhp on 2016/11/22.
 */

public class Fragment_Recommend extends Fragment {
    private View view;
    private List<Data_Recommend> ls = new ArrayList<Data_Recommend>();
    private Adapter_Recommend recommednAdapter;
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
        recommednAdapter = new Adapter_Recommend(getActivity(),ls);
        lv.setAdapter(recommednAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
            }
        });
    }

    private void getData() {
        ls.add(new Data_Recommend(0L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭11111111111"));
        ls.add(new Data_Recommend(1L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭。"));
        ls.add(new Data_Recommend(2L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭"));
        ls.add(new Data_Recommend(3L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭"));
        ls.add(new Data_Recommend(4L,"1","茶泡饭","茶泡饭","茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭茶泡饭"));
    }
}
