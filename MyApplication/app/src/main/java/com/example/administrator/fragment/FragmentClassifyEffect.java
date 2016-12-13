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

import com.example.administrator.adapter.AdapterEffect;
import com.example.administrator.domain.DataEffect;
import com.example.administrator.myapplication.ClassificationListActivity;
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
    private TextView Tvname;

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

        gv_effect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tvname = (TextView)view.findViewById(R.id.Tv_effect);
                Intent intent = new Intent(getActivity(), ClassificationListActivity.class);
                intent.putExtra("NAME",Tvname.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void getData() {
        ldeffect.add((new DataEffect(1,R.drawable.duikangwumai,"对抗雾霾")));
        ldeffect.add((new DataEffect(2,R.drawable.fangfushe,"防辐射")));
        ldeffect.add((new DataEffect(3,R.drawable.runchangtongbian,"润肠通便")));
        ldeffect.add((new DataEffect(4,R.drawable.mingmu,"明目")));
        ldeffect.add((new DataEffect(5,R.drawable.runfeizhike,"润肺止咳")));
        ldeffect.add((new DataEffect(6,R.drawable.tongjing,"痛经")));
        ldeffect.add((new DataEffect(7,R.drawable.qushi,"祛湿")));
        ldeffect.add((new DataEffect(8,R.drawable.ziyinzhuangyang,"滋阴壮阳")));
        ldeffect.add((new DataEffect(8,R.drawable.neifenmi,"调节内分泌")));
        ldeffect.add((new DataEffect(8,R.drawable.qiudongjinbu,"秋冬进补")));
        ldeffect.add((new DataEffect(8,R.drawable.quhannuanshen,"驱寒暖身")));
        ldeffect.add((new DataEffect(8,R.drawable.xiaoshujieke,"消暑解渴")));
        ldeffect.add((new DataEffect(8,R.drawable.qingrequhuo,"清热去火")));
        ldeffect.add((new DataEffect(8,R.drawable.ruanchangtongbian,"健脾养胃")));
        ldeffect.add((new DataEffect(8,R.drawable.tigaomianyi,"提高免疫")));
        ldeffect.add((new DataEffect(8,R.drawable.jiyili,"增强记忆力")));
    }

}
