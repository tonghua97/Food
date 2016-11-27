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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.adapter.AdapterRanklist;
import com.example.administrator.domain.DataRanklist;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.RecipeShowActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 * 排行榜fragment
 */
public class FragmentHomeRank extends Fragment{
    private ArrayAdapter<String> adapter;
    private List<DataRanklist> lrl = new ArrayList<>();
    private ListView lv_ranklist;
    private com.example.administrator.adapter.AdapterRanklist adapter_ranklist;
    private boolean isFirst = true;
    private View view;
    private TextView Tvname;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ranklist, container, false);
        getViews();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isFirst){
            getData();

            isFirst = false;
        }

        adapter_ranklist = new AdapterRanklist(lrl, getActivity());
        lv_ranklist.setAdapter(adapter_ranklist);

        lv_ranklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //美食名称
                Tvname = (TextView)view.findViewById(R.id.Tv_ranklist_recipename);

                Intent intent = new Intent(getActivity(), RecipeShowActivity.class);
                intent.putExtra("NAME",Tvname.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void getData() {
        lrl.add(new DataRanklist(1L, 1, 289, "这里放图片url", "老北京炸酱面", "1"));
        lrl.add(new DataRanklist(2L, 2, 204, "这里放图片url", "老北京炸酱面", "2"));
        lrl.add(new DataRanklist(3L, 3, 179, "这里放图片url", "老北京炸酱面", "3"));
        lrl.add(new DataRanklist(4L, 4, 120, "这里放图片url", "老北京炸酱面", "4"));
        lrl.add(new DataRanklist(5L, 5, 106, "这里放图片url", "老北京炸酱面", "5"));

    }

    public void getViews() {
        //listview
        lv_ranklist = (ListView) view.findViewById(R.id.Lv_ranklist);
    }
}

