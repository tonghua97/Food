package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.administrator.domain.DataMaterial;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 姜佳妮 on 2016/11/29.
 * 描述：食材Adapter
 */
public class AdapterMaterial extends BaseAdapter {
    private Context context;
    private List<DataMaterial> list = new ArrayList<>();
    public AdapterMaterial(Context c,List<DataMaterial> ls) {
        list = ls;
        context = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item_material,null);
        CheckBox CheckBox=(CheckBox)convertView.findViewById(R.id.material_checkbox);
        CheckBox.setText(list.get(position).getName());
        return convertView;
    }
}
