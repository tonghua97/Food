package com.example.administrator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.administrator.adapter.AdapterCuisine;
import com.example.administrator.domain.DataCuisine;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园 on 2016/11/24.
 */

public class FragmentCuisine extends Fragment {
    private ArrayAdapter<String> adapter;
    private List<DataCuisine> ldcuisine = new ArrayList<>();
    private GridView gv_cuisine;
    private com.example.administrator.adapter.AdapterCuisine AdapterCuisine;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuisine,container,false);
        AdapterCuisine = new AdapterCuisine(ldcuisine,getActivity());
        gv_cuisine = (GridView)view.findViewById(R.id.Gv_cuisine);
        gv_cuisine.setAdapter(AdapterCuisine);
        getData();
        return view;
    }

    private void getData() {
        ldcuisine.add((new DataCuisine(1,R.drawable.icon_home_normal,"鲁菜")));
        ldcuisine.add((new DataCuisine(2,R.drawable.icon_home_normal,"鲁菜")));
        ldcuisine.add((new DataCuisine(3,R.drawable.icon_home_normal,"鲁菜")));
        ldcuisine.add((new DataCuisine(4,R.drawable.icon_home_normal,"鲁菜")));
        ldcuisine.add((new DataCuisine(5,R.drawable.icon_home_normal,"鲁菜")));
        ldcuisine.add((new DataCuisine(6,R.drawable.icon_home_normal,"鲁菜")));
        ldcuisine.add((new DataCuisine(7,R.drawable.icon_home_normal,"鲁菜")));
        ldcuisine.add((new DataCuisine(8,R.drawable.icon_home_normal,"鲁菜")));
    }

}
