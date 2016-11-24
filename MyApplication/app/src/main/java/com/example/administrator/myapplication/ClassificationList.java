package com.example.administrator.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.adapter.AdapterClassificationList;
import com.example.administrator.domain.DataClassificationList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/24.
 */

public class ClassificationList {
    private ArrayAdapter<String> adapter;
    private List<DataClassificationList> ldc = new ArrayList<>();
    private ListView lv_classification;
    private AdapterClassificationList adapter_classification;
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_classificationlist,container,false);
        adapter_classification = new AdapterClassificationList(ldc);
        lv_classification = (ListView)view.findViewById(R.id.Lv_classification);
        lv_classification.setAdapter(adapter_classification);
        getData();
        return view;
    }

    private void getData() {
        ldc.add((new DataClassificationList(0L,R.drawable.img_loading,"食趣","食趣",666666,66)));
        ldc.add((new DataClassificationList(1L,R.drawable.img_loading,"食趣","食趣",666666,66)));
        ldc.add((new DataClassificationList(2L,R.drawable.img_loading,"食趣","食趣",666666,66)));
        ldc.add((new DataClassificationList(3L,R.drawable.img_loading,"食趣","食趣",666666,66)));
        ldc.add((new DataClassificationList(4L,R.drawable.img_loading,"食趣","食趣",666666,66)));
        ldc.add((new DataClassificationList(5L,R.drawable.img_loading,"食趣","食趣",666666,66)));
        ldc.add((new DataClassificationList(6L,R.drawable.img_loading,"食趣","食趣",666666,66)));

    }
}
