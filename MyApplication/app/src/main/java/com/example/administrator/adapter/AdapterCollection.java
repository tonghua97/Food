package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataCollection;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/23.
 */

public class AdapterCollection extends BaseAdapter {
    private Context context;
    private List<DataCollection> ldc = new ArrayList<>();
    public AdapterCollection(List<DataCollection> ld,Context context){
        ldc = ld;
        this.context = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_collection_shipu,null);
        }
        TextView Tv_collection_name = (TextView)view.findViewById(R.id.Tv_collection_name);
        Tv_collection_name.setText(ldc.get(position).getName());
        TextView Tv_collection_introduction = (TextView) view.findViewById(R.id.Tv_collection_introduction);
        Tv_collection_introduction.setText(ldc.get(position).getIntroduction());
        ImageView Im_collection_image = (ImageView) view.findViewById(R.id.Iv_collection);
        Im_collection_image.setImageResource(ldc.get(position).getImage());
        return view;
    }
}
