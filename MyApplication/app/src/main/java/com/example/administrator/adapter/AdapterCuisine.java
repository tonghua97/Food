package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataCuisine;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园 on 2016/11/24.
 */

public class AdapterCuisine extends BaseAdapter {
    private Context context;
    private List<DataCuisine> ldcuisine = new ArrayList<>();
    public AdapterCuisine(List<DataCuisine> ld, Context context){
        ldcuisine = ld;
        this.context = context;
    }
    @Override
    public int getCount() {
        return ldcuisine.size();
    }

    @Override
    public Object getItem(int position) {
        return ldcuisine.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldcuisine.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.gridview_item_cuisine,null);
        }
        ImageView Iv_cuisine = (ImageView) view.findViewById(R.id.Iv_cuisine);
        Iv_cuisine.setImageResource(ldcuisine.get(position).getUrl());
        TextView Tv_cuisine = (TextView) view.findViewById(R.id.Tv_cuisine);
        Tv_cuisine.setText(ldcuisine.get(position).getmCuisine_Name());
        return view;
    }
}