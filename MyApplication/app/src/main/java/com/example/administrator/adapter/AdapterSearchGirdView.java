package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class AdapterSearchGirdView extends BaseAdapter {
    private Context context;
    private List<String> list;

    public AdapterSearchGirdView(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_search_girdview, null);
        }

        TextView Tv = (TextView)view.findViewById(R.id.search_girdview_Tv_name);
        Tv.setText(list.get(i));
        return view;
    }
}
