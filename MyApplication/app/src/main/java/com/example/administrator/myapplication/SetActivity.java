package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class SetActivity extends Activity {

    private Button BtBack;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);

        //获取控件
        getViews();

        //设置监听
        setListener();
    }

    private void setListener() {
        BtBack.setOnClickListener(myListener);
    }

    public void getViews() {
        BtBack = (Button)findViewById(R.id.Setting_Bt_Back);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Setting_Bt_Back:
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            Instrumentation inst = new Instrumentation();
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        }
                    }.start();
                    break;
            }
        }
    };

}
