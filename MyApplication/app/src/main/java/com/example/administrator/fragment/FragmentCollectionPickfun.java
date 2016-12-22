package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.adapter.AdapterCollectionPickfun;
import com.example.administrator.domain.DataCollectionPickfun;
import com.example.administrator.myapplication.PickfundetailActivity;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园on 2016/12/21.
 */

public class FragmentCollectionPickfun extends Fragment{

    private List<DataCollectionPickfun> ldcp = new ArrayList<>();
    private ListView lv_collection_pickfun;
    private AdapterCollectionPickfun collectionPickfun_adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection_pickfun,container,false);
        collectionPickfun_adapter = new AdapterCollectionPickfun(ldcp,getActivity());
        lv_collection_pickfun = (ListView)view.findViewById(R.id.Lv_collection_pickfun);
        lv_collection_pickfun.setAdapter(collectionPickfun_adapter);
        setListener();
        getData();
        return view;
    }

    private void setListener() {
        lv_collection_pickfun.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),PickfundetailActivity.class);
                startActivity(intent);
            }
        });
    }
        private void getData() {
            ldcp.add((new DataCollectionPickfun(0L,R.drawable.img_loading,"食趣","食趣")));
            ldcp.add((new DataCollectionPickfun(1L,R.drawable.img_loading,"食趣","食趣")));
            ldcp.add((new DataCollectionPickfun(2L,R.drawable.img_loading,"食趣","食趣")));
            ldcp.add((new DataCollectionPickfun(3L,R.drawable.img_loading,"食趣","食趣")));
            ldcp.add((new DataCollectionPickfun(0L,R.drawable.img_loading,"食趣","食趣")));
            ldcp.add((new DataCollectionPickfun(1L,R.drawable.img_loading,"食趣","食趣")));
            ldcp.add((new DataCollectionPickfun(2L,R.drawable.img_loading,"食趣","食趣")));
            ldcp.add((new DataCollectionPickfun(3L,R.drawable.img_loading,"食趣","食趣")));

        }

}
