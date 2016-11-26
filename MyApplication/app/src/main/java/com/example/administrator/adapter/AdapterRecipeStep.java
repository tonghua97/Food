package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.domain.DataRecipeStep;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wcyhp on 2016/11/24.
 */

public class AdapterRecipeStep extends BaseAdapter {
    private Context mContext;
    private List<DataRecipeStep> mData = new ArrayList<>();

    public AdapterRecipeStep(Context context, List<DataRecipeStep> mData) {
        this.mContext = context;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mData.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_recipeshow_step,null);

        TextView Step_No = (TextView)view.findViewById(R.id.Tv_recipeshow_step_No);
        Step_No.setText(mData.get(i).getNo());
        TextView Step_content = (TextView)view.findViewById(R.id.Tv_recipeshow_step_content);
        Step_content.setText(mData.get(i).getStep());
        return view;
    }
}
