package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataClassificationList;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/11/24.
 */

public class AdapterClassificationList extends BaseAdapter {
    private Context context;
    private List<DataClassificationList> ldcla = new ArrayList<>();
    public AdapterClassificationList(List<DataClassificationList> ldc,Context context){
        ldcla = ldc;
        this.context = context;
    }
    @Override
    public int getCount() {
        return ldcla.size();
    }

    @Override
    public Object getItem(int position) {
        return ldcla.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldcla.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_classificationlist,null);
        }
        TextView Tv_classification_time = (TextView)view.findViewById(R.id.Tv_classification_time);
        Tv_classification_time.setText(ldcla.get(position).getTime() + "");
        TextView Tv_classification_number = (TextView)view.findViewById(R.id.Tv_classification_collection);
        Tv_classification_number.setText(ldcla.get(position).getNumber() + "");
        TextView Tv_classification_name = (TextView)view.findViewById(R.id.Tv_classification_name);
        Tv_classification_name.setText(ldcla.get(position).getName());
        TextView Tv_classification_material = (TextView) view.findViewById(R.id.Tv_classification_material);
        Tv_classification_material.setText(ldcla.get(position).getMaterial());
        ImageView Im_classification_image = (ImageView) view.findViewById(R.id.Iv_classification);
        Im_classification_image.setImageResource(ldcla.get(position).getImage());
        return view;
    }
}
