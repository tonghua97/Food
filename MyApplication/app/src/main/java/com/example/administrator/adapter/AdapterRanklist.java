package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.domain.DataRanklist;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sophia on 2016/11/24.
 */
public class AdapterRanklist extends BaseAdapter {
    private Context context;
    private List<DataRanklist> lrl = new ArrayList<>();

    public AdapterRanklist(List<DataRanklist> lrl,Context context){
        this.lrl = lrl;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lrl.size();
    }

    @Override
    public Object getItem(int position) {
        return lrl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lrl.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_ranklist,null);
        }
        //食谱收藏排行
        TextView Tv_rank_id = (TextView)convertView.findViewById(R.id.Tv_rank_id);
        Tv_rank_id.setText(lrl.get(position).getRank());
        //获取排行待解决 2016/11/23 ginger lrl.get(position).getRank()

        //食谱收藏数 Tv_ranklist_collect
        TextView Tv_ranklist_collect = (TextView)convertView.findViewById(R.id.Tv_ranklist_collect);
        Tv_ranklist_collect.setText(lrl.get(position).getNum());

        //食谱图片 Iv_ranklist_recipeimg
        ImageView Iv_ranklist_recipeimg = (ImageView)convertView.findViewById((R.id.Iv_ranklist_recipeimg));
        String imgurl = lrl.get(position).getImage();

        //食谱名 Tv_ranklist_recipename
        TextView Tv_pickfun_title = (TextView) convertView.findViewById(R.id.Tv_pickfun_title);
        Tv_pickfun_title.setText(lrl.get(position).getName());

        return convertView;
    }
}
