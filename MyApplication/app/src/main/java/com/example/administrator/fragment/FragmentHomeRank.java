package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.adapter.AdapterRanklist;
import com.example.administrator.domain.DataRanklist;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class FragmentHomeRank extends Fragment{
    private ArrayAdapter<String> adapter;
    private List<DataRanklist> lrl = new ArrayList<>();
    private ListView lv_ranklist;
    private com.example.administrator.adapter.AdapterRanklist adapter_ranklist;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_item_ranklist, container, false);
        lv_ranklist = (ListView) view.findViewById(R.id.Lv_ranklist);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        adapter_ranklist = new AdapterRanklist(lrl, getActivity());
        lv_ranklist.setAdapter(adapter_ranklist);
        lv_ranklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
            }
        });
    }

    private void getData() {
        lrl.add(new DataRanklist(1L, 1, 289, "这里放图片url", "老北京炸酱面1", "1"));
        lrl.add(new DataRanklist(2L, 2, 204, "这里放图片url", "老北京炸酱面2", "2"));
        lrl.add(new DataRanklist(3L, 3, 179, "这里放图片url", "老北京炸酱面3", "3"));
        lrl.add(new DataRanklist(4L, 4, 120, "这里放图片url", "老北京炸酱面4", "4"));
        lrl.add(new DataRanklist(5L, 5, 106, "这里放图片url", "老北京炸酱面5", "5"));
        lrl.add(new DataRanklist(6L, 6, 106, "这里放图片url", "老北京炸酱面6", "6"));
        lrl.add(new DataRanklist(7L, 7, 106, "这里放图片url", "老北京炸酱面7", "7"));
        lrl.add(new DataRanklist(8L, 8, 106, "这里放图片url", "老北京炸酱面8", "8"));
        lrl.add(new DataRanklist(9L, 9, 106, "这里放图片url", "老北京炸酱面9", "9"));
        lrl.add(new DataRanklist(10L, 10, 106, "这里放图片url", "老北京炸酱面10", "10"));
    }


}

