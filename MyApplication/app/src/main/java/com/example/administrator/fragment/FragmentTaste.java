package com.example.administrator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.administrator.adapter.AdapterTaste;
import com.example.administrator.domain.DataTaste;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园 on 2016/11/24.
 */

public class FragmentTaste extends Fragment {
    private ArrayAdapter<String> adapter;
    private List<DataTaste> ldtaste = new ArrayList<>();
    private GridView gv_taste;
    private com.example.administrator.adapter.AdapterTaste AdapterTaste;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taste,container,false);
        AdapterTaste = new AdapterTaste(ldtaste,getActivity());
        gv_taste = (GridView)view.findViewById(R.id.Gv_taste);
        gv_taste.setAdapter(AdapterTaste);
        getData();
        return view;
    }

    private void getData() {
        ldtaste.add((new DataTaste(1,R.drawable.icon_home_normal,"鲁菜")));
        ldtaste.add((new DataTaste(2,R.drawable.icon_home_normal,"鲁菜")));
        ldtaste.add((new DataTaste(3,R.drawable.icon_home_normal,"鲁菜")));
        ldtaste.add((new DataTaste(4,R.drawable.icon_home_normal,"鲁菜")));
        ldtaste.add((new DataTaste(5,R.drawable.icon_home_normal,"鲁菜")));
        ldtaste.add((new DataTaste(6,R.drawable.icon_home_normal,"鲁菜")));
        ldtaste.add((new DataTaste(7,R.drawable.icon_home_normal,"鲁菜")));
        ldtaste.add((new DataTaste(8,R.drawable.icon_home_normal,"鲁菜")));
    }

}
