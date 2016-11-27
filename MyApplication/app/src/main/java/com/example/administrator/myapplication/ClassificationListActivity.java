package com.example.administrator.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterClassificationList;
import com.example.administrator.domain.DataClassificationList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/24.
 * 分类列表页
 */

public class ClassificationListActivity extends Activity{
    private ArrayAdapter<String> adapter;
    private List<DataClassificationList> ldc = new ArrayList<>();
    private ListView lv_classification;
    private AdapterClassificationList adapter_classification;
    private TextView Tvname;
    private Button BtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classificationlist);

        //获取控件
        getViews();
        //获取点击事件的美食类型名字
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        //获取数据
        getData();
        //设置Tvname
        Tvname.setText(name);
        //设置adapter
        adapter_classification = new AdapterClassificationList(ldc,this);
        lv_classification.setAdapter(adapter_classification);
        //设置返回监听
        BtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    public void getViews() {
        lv_classification = (ListView)findViewById(R.id.Lv_classification);
        Tvname = (TextView)findViewById(R.id.classification_Tv_name);
        BtBack = (Button)findViewById(R.id.classification_Bt_Back);
    }
}
