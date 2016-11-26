package com.example.administrator.myapplication;


import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.adapter.AdapterCollection;
import com.example.administrator.domain.DataCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/23.
 * 收藏Activity
 */

public class CollectionActivity extends Activity{
    private ArrayAdapter<String> adapter;
    private List<DataCollection> ld = new ArrayList<>();
    private ListView lv_collection;
    private AdapterCollection adapter_collection;
    private Button BtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        //获取控件
        getViews();
        //获取数据
        getData();
        //设置adapter
        adapter_collection = new AdapterCollection(ld,this);
        lv_collection.setAdapter(adapter_collection);

        //设置监听
        setListener();
    }

    private void setListener() {
        BtBack.setOnClickListener(myListener);
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

    public void getViews() {
        lv_collection = (ListView)findViewById(R.id.Lv_collection);
        BtBack = (Button)findViewById(R.id.collection_Bt_Back);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.collection_Bt_Back:
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            Instrumentation inst = new Instrumentation();
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        }
                    }.start();
                    break;
            }
        }
    };
}
