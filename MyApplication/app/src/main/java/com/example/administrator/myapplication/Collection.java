package com.example.administrator.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.adapter.AdapterCollection;
import com.example.administrator.domain.DataCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/23.
 */

public class Collection {
    private ArrayAdapter<String> adapter;
    private List<DataCollection> ld = new ArrayList<>();
    private ListView lv_collection;
    private AdapterCollection adapter_collection;
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_collection,container,false);
        adapter_collection = new AdapterCollection(ld);
        lv_collection = (ListView)view.findViewById(R.id.Lv_collection);
        lv_collection.setAdapter(adapter_collection);
        getData();
        return view;
    }

    private void getData() {
        ld.add((new DataCollection(0L,R.drawable.img_loading,"食趣","食趣")));
        ld.add((new DataCollection(1L,R.drawable.img_loading,"食趣","食趣")));
        ld.add((new DataCollection(2L,R.drawable.img_loading,"食趣","食趣")));
        ld.add((new DataCollection(3L,R.drawable.img_loading,"食趣","食趣")));
        ld.add((new DataCollection(0L,R.drawable.img_loading,"食趣","食趣")));
        ld.add((new DataCollection(1L,R.drawable.img_loading,"食趣","食趣")));
        ld.add((new DataCollection(2L,R.drawable.img_loading,"食趣","食趣")));
        ld.add((new DataCollection(3L,R.drawable.img_loading,"食趣","食趣")));

    }
}
