package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataCollectionRecipe;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13173 on 2016/11/22.
 */

public class AdapterCollectionRecipe extends BaseAdapter {
    private Context context;
    private List<DataCollectionRecipe> ldcr = new ArrayList<>();

    public AdapterCollectionRecipe(List<DataCollectionRecipe> ld,Context c){
        ldcr = ld;
        context = c;
    }
    @Override
    public int getCount() {
        return ldcr.size();
    }

    @Override
    public Object getItem(int position) {
        return ldcr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ldcr.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(null == view){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_collection_recipe,null);
        }
        ImageView Iv_Cpickfun = (ImageView)view.findViewById(R.id.Iv_collection_recipe);
        Iv_Cpickfun.setImageResource(ldcr.get(position).getImage());
        TextView Tv_Cpname = (TextView)view.findViewById(R.id.Tv_collection_name1);
        Tv_Cpname.setText(ldcr.get(position).getName());
        TextView Tv_Cpintroduction = (TextView)view.findViewById(R.id.Tv_collection_introduction1);
        Tv_Cpintroduction.setText(ldcr.get(position).getIntroduction());
        return view;
    }
}
