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
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterTaste;
import com.example.administrator.domain.DataTaste;
import com.example.administrator.myapplication.ClassificationListActivity;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园 on 2016/11/24.
 * 分类-口味fragment
 */

public class FragmentClassifyTaste extends Fragment {
    private ArrayAdapter<String> adapter;
    private List<DataTaste> ldtaste = new ArrayList<>();
    private GridView gv_taste;
    private com.example.administrator.adapter.AdapterTaste AdapterTaste;
    private boolean isFirst = true;
    private View view;
    private TextView Tvname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_taste,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isFirst){
            getData();
            isFirst = false;
        }

        AdapterTaste = new AdapterTaste(ldtaste,getActivity());
        gv_taste = (GridView)view.findViewById(R.id.Gv_taste);
        gv_taste.setAdapter(AdapterTaste);

        gv_taste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tvname = (TextView)view.findViewById(R.id.Tv_taste);
                Intent intent = new Intent(getActivity(), ClassificationListActivity.class);
                intent.putExtra("NAME",Tvname.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void getData() {
        ldtaste.add((new DataTaste(1,R.drawable.suan,"酸")));
        ldtaste.add((new DataTaste(2,R.drawable.tian,"甜")));
        ldtaste.add((new DataTaste(3,R.drawable.la,"辣")));
        ldtaste.add((new DataTaste(4,R.drawable.xian,"咸")));
        ldtaste.add((new DataTaste(5,R.drawable.xiang2,"香")));
        ldtaste.add((new DataTaste(6,R.drawable.ku,"苦")));
        ldtaste.add((new DataTaste(7,R.drawable.gali,"咖喱")));
        ldtaste.add((new DataTaste(8,R.drawable.mala1,"麻辣")));
        ldtaste.add((new DataTaste(8,R.drawable.ziran,"孜然")));
        ldtaste.add((new DataTaste(8,R.drawable.qingdan,"清淡")));
        ldtaste.add((new DataTaste(8,R.drawable.suanla,"酸辣")));
        ldtaste.add((new DataTaste(8,R.drawable.xiangla,"香辣")));
        ldtaste.add((new DataTaste(8,R.drawable.suantian,"酸甜")));
        ldtaste.add((new DataTaste(8,R.drawable.xiangsu,"香酥")));
        ldtaste.add((new DataTaste(8,R.drawable.naixiang,"奶香")));
        ldtaste.add((new DataTaste(8,R.drawable.yvxiang,"鱼香")));
        ldtaste.add((new DataTaste(8,R.drawable.wuxiang,"五香")));
        ldtaste.add((new DataTaste(8,R.drawable.jiaoyan,"椒盐")));
        ldtaste.add((new DataTaste(8,R.drawable.shuangkou,"爽口")));
    }

}
