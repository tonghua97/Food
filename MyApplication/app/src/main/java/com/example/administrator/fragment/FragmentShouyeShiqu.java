package com.example.administrator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.adapter.adapter_pickfun;
import com.example.administrator.domain.Data_pickfun;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class FragmentShouyeShiqu extends Fragment{
    private ArrayAdapter<String> adapter;
    private List<Data_pickfun> ldp = new ArrayList<>();
    private ListView lv_pickfun;
    private com.example.administrator.adapter.adapter_pickfun adapter_pickfun;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pickfun,container,false);
        adapter_pickfun = new adapter_pickfun(ldp,getActivity());
        lv_pickfun = (ListView)view.findViewById(R.id.Lv_pickfun);
        lv_pickfun.setAdapter(adapter_pickfun);
        getData();
        return view;
    }

    private void getData() {
        ldp.add((new Data_pickfun(1,R.drawable.img_loading,"面包的由来")));
        ldp.add((new Data_pickfun(2,R.drawable.img_loading,"面包的由来")));
        ldp.add((new Data_pickfun(3,R.drawable.img_loading,"面包的由来")));
        ldp.add((new Data_pickfun(4,R.drawable.img_loading,"面包的由来")));
        ldp.add((new Data_pickfun(5,R.drawable.img_loading,"面包的由来")));
    }

}
