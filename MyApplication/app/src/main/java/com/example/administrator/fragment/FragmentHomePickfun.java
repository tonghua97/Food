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

import com.example.administrator.adapter.AdapterPickfun;
import com.example.administrator.domain.DataPickfun;
import com.example.administrator.myapplication.PickfundetailActivity;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 * 拾趣fragment
 */
public class FragmentHomePickfun extends Fragment{
    private ArrayAdapter<String> adapter;
    private List<DataPickfun> ldp = new ArrayList<>();
    private ListView lv_pickfun;
    private AdapterPickfun AdapterPickfun;
    private boolean isFirst = true;
    private View view;
    private TextView TvTile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pickfun,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (isFirst){
            getData();
            isFirst = false;
        }

        AdapterPickfun = new AdapterPickfun(ldp,getActivity());
        lv_pickfun = (ListView)view.findViewById(R.id.Lv_pickfun);
        lv_pickfun.setAdapter(AdapterPickfun);

        lv_pickfun.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TvTile = (TextView)view.findViewById(R.id.Tv_pickfun_title);
                Intent intent = new Intent(getActivity(), PickfundetailActivity.class);
                intent.putExtra("TITLE",TvTile.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void getData() {
        ldp.add((new DataPickfun(1,R.drawable.img_loading,"面包的由来")));
        ldp.add((new DataPickfun(2,R.drawable.img_loading,"面包的由来")));
        ldp.add((new DataPickfun(3,R.drawable.img_loading,"面包的由来")));
        ldp.add((new DataPickfun(4,R.drawable.img_loading,"面包的由来")));
        ldp.add((new DataPickfun(5,R.drawable.img_loading,"面包的由来")));
    }

}
