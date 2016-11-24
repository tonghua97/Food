package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataEffect;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园 on 2016/11/24.
 */

public class AdapterEffect extends BaseAdapter {
    private Context context;
    private List<DataEffect> ldeffect = new ArrayList<>();
    public AdapterEffect(List<DataEffect> ld, Context context){
        ldeffect = ld;
        this.context = context;
    }
    @Override
    public int getCount() {
        return ldeffect.size();
    }

    @Override
    public Object getItem(int position) {
        return ldeffect.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldeffect.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.gridview_item_effect,null);
        }
        ImageView Iv_effect = (ImageView) view.findViewById(R.id.Iv_effect);
        Iv_effect.setImageResource(ldeffect.get(position).getUrl());
        TextView Tv_effect = (TextView) view.findViewById(R.id.Tv_effect);
        Tv_effect.setText(ldeffect.get(position).getmEffect_Name());
        return view;
    }
}