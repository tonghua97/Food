package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataTaste;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园 on 2016/11/24.
 */

public class AdapterTaste extends BaseAdapter {
    private Context context;
    private List<DataTaste> ldtaste = new ArrayList<>();
    public AdapterTaste(List<DataTaste> ld, Context context){
        ldtaste = ld;
        this.context = context;
    }
    @Override
    public int getCount() {
        return ldtaste.size();
    }

    @Override
    public Object getItem(int position) {
        return ldtaste.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldtaste.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.gridview_item_taste,null);
        }
        ImageView Iv_taste = (ImageView) view.findViewById(R.id.Iv_taste);
        Iv_taste.setImageResource(ldtaste.get(position).getUrl());
        TextView Tv_taste = (TextView) view.findViewById(R.id.Tv_taste);
        Tv_taste.setText(ldtaste.get(position).getmTaste_Name());
        return view;
    }
}