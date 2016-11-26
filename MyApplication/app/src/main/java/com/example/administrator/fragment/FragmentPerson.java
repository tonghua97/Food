package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.myapplication.CollectionActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.SetActivity;

/**
 * Created by Administrator on 2016/11/22.
 * 个人中心fragment
 */
public class FragmentPerson extends Fragment{
    private View view;
    private RelativeLayout title1;
    private RelativeLayout title2;
    private RelativeLayout title3;
    private RelativeLayout title4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_personal,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getViews();

        setListener();

    }

    private void setListener() {
        title1.setOnClickListener(myListener);
        title2.setOnClickListener(myListener);
        title3.setOnClickListener(myListener);
        title4.setOnClickListener(myListener);
    }

    public void getViews() {
        //收藏
        title1 = (RelativeLayout)view.findViewById(R.id.personal_rl_title1);
        //设置
        title2 = (RelativeLayout)view.findViewById(R.id.personal_rl_title2);
        //关于
        title3 = (RelativeLayout)view.findViewById(R.id.personal_rl_title3);
        //反馈
        title4 = (RelativeLayout)view.findViewById(R.id.personal_rl_title4);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.personal_rl_title1:
                    Intent intent1 = new Intent(getActivity(), CollectionActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.personal_rl_title2:
                    Intent intent2 = new Intent(getActivity(), SetActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.personal_rl_title3:
                    break;
                case R.id.personal_rl_title4:
                    break;
            }
        }
    };
}
