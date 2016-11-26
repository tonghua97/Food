package com.example.administrator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.administrator.adapter.AdapterEffect;
import com.example.administrator.domain.DataEffect;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园 on 2016/11/24.
 * 分类-功效fragment
 */

public class FragmentClassifyEffect extends Fragment {
    private ArrayAdapter<String> adapter;
    private List<DataEffect> ldeffect = new ArrayList<>();
    private GridView gv_effect;
    private com.example.administrator.adapter.AdapterEffect AdapterEffect;
    private boolean isFirst = true;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_effect,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isFirst){
            getData();
            isFirst = false;
        }

        AdapterEffect = new AdapterEffect(ldeffect,getActivity());
        gv_effect = (GridView)view.findViewById(R.id.Gv_effect);
        gv_effect.setAdapter(AdapterEffect);

    }

    private void getData() {
        ldeffect.add((new DataEffect(1,R.drawable.icon_home_normal,"增强记忆力")));
        ldeffect.add((new DataEffect(2,R.drawable.icon_home_normal,"增强免疫力")));
        ldeffect.add((new DataEffect(3,R.drawable.icon_home_normal,"缓解疲劳")));
        ldeffect.add((new DataEffect(4,R.drawable.icon_home_normal,"增强体质")));
        ldeffect.add((new DataEffect(5,R.drawable.icon_home_normal,"延缓衰老")));
        ldeffect.add((new DataEffect(6,R.drawable.icon_home_normal,"滋补养生")));
        ldeffect.add((new DataEffect(7,R.drawable.icon_home_normal,"促进消化")));
        ldeffect.add((new DataEffect(8,R.drawable.icon_home_normal,"清热解毒")));
        ldeffect.add((new DataEffect(8,R.drawable.icon_home_normal,"增强抵抗力")));
    }

}
