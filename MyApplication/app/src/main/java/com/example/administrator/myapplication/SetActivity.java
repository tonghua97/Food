package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.fragment.FragmentFenlei;
import com.example.administrator.fragment.FragmentShouye;
import com.example.administrator.fragment.FragmentWode;


public class SetActivity extends Activity {

    private Button BtBack;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);

        //获取控件
        getViews();
    }


    public void getViews() {
        BtBack = (Button)findViewById(R.id.Setting_Bt_Back);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Setting_Bt_Back:
                    break;
            }
        }
    };
}
