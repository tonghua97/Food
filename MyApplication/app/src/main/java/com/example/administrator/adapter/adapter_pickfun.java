package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.domain.Data_pickfun;
import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13173 on 2016/11/22.
 */

public class adapter_pickfun extends BaseAdapter {
    private Context context;
    private List<Data_pickfun> ldp = new ArrayList<>();
    public adapter_pickfun(List<Data_pickfun> ld){
        ldp = ld;
    }
    @Override
    public int getCount() {
        return ldp.size();
    }

    @Override
    public Object getItem(int position) {
        return ldp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldp.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_pickfun,null);
        }
        TextView Tv_pickfun_title = (TextView) view.findViewById(R.id.Tv_pickfun_title);
        Tv_pickfun_title.setText(ldp.get(position).getmPickfun_Name());
        RelativeLayout Rl_pickfun = (RelativeLayout) view.findViewById(R.id.Rl_pickfun);
        Rl_pickfun.setBackgroundResource(ldp.get(position).getUrl());
        return view;
    }
}
