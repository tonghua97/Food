package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.Data_collection;
import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/23.
 */

public class Adapter_collection extends BaseAdapter {
    private Context context;
    private List<Data_collection> ldc = new ArrayList<>();
    public Adapter_collection(List<Data_collection> ld){
        ldc = ld;
    }
    @Override
    public int getCount() {
        return ldc.size();
    }

    @Override
    public Object getItem(int position) {
        return ldc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldc.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_collection,null);
        }
        TextView Tv_collection_name = (TextView)view.findViewById(R.id.Tv_collection1);
        Tv_collection_name.setText(ldc.get(position).getName());
        TextView Tv_collection_introduction = (TextView) view.findViewById(R.id.Tv_collection2);
        Tv_collection_introduction.setText(ldc.get(position).getIntroduction());
        ImageView Im_collection_image = (ImageView) view.findViewById(R.id.Iv_collection);
        Im_collection_image.setImageResource(ldc.get(position).getImage());
        return view;
    }
}
