package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.adapter.AdapterCuisine;
import com.example.administrator.domain.DataCuisine;
import com.example.administrator.myapplication.ClassificationListActivity;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园 on 2016/11/24.
 * 分类-菜系fragment
 */

public class FragmentClassifyCuisine extends Fragment {
    private ArrayAdapter<String> adapter;
    private List<DataCuisine> ldcuisine = new ArrayList<>();
    private GridView gv_cuisine;
    private com.example.administrator.adapter.AdapterCuisine AdapterCuisine;
    private boolean isFirst = true;
    private View view;
    private TextView TvCuisine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cuisine,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isFirst){
            getData();
            isFirst = false;
        }

        AdapterCuisine = new AdapterCuisine(ldcuisine,getActivity());
        gv_cuisine = (GridView)view.findViewById(R.id.Gv_cuisine);
        gv_cuisine.setAdapter(AdapterCuisine);

        gv_cuisine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TvCuisine = (TextView)view.findViewById(R.id.Tv_cuisine);
                Intent intent = new Intent(getActivity(), ClassificationListActivity.class);
                intent.putExtra("NAME",TvCuisine.getText().toString());
                startActivity(intent);
            }
        });

    }

    private void getData() {
        ldcuisine.add((new DataCuisine(1,R.drawable.chuan,"川菜")));
        ldcuisine.add((new DataCuisine(2,R.drawable.xiang,"湘菜")));
        ldcuisine.add((new DataCuisine(3,R.drawable.yue,"粤菜")));
        ldcuisine.add((new DataCuisine(4,R.drawable.su,"苏菜")));
        ldcuisine.add((new DataCuisine(5,R.drawable.hui,"徽菜")));
        ldcuisine.add((new DataCuisine(6,R.drawable.zhe,"浙菜")));
        ldcuisine.add((new DataCuisine(7,R.drawable.lu,"鲁菜")));
        ldcuisine.add((new DataCuisine(8,R.drawable.dongbei,"东北菜")));
        ldcuisine.add((new DataCuisine(9,R.drawable.min,"闽菜")));
        ldcuisine.add((new DataCuisine(10,R.drawable.jing,"京菜")));
        ldcuisine.add((new DataCuisine(11,R.drawable.shanghai,"上海菜")));
        ldcuisine.add((new DataCuisine(12,R.drawable.qingzhen,"清真")));
        ldcuisine.add((new DataCuisine(13,R.drawable.xinjiang,"新疆菜")));
        ldcuisine.add((new DataCuisine(14,R.drawable.yv,"豫菜")));
        ldcuisine.add((new DataCuisine(15,R.drawable.france,"法国菜")));
        ldcuisine.add((new DataCuisine(16,R.drawable.italy3,"意大利菜")));
        ldcuisine.add((new DataCuisine(17,R.drawable.japan,"日本料理")));
        ldcuisine.add((new DataCuisine(18,R.drawable.korea,"韩国料理")));
        ldcuisine.add((new DataCuisine(19,R.drawable.thailand,"泰国菜")));
        ldcuisine.add((new DataCuisine(20,R.drawable.qita,"其他")));
    }

}
