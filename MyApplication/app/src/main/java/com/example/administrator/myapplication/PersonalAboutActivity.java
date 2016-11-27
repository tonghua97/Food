package com.example.administrator.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 关于页面
 */
public class PersonalAboutActivity extends Activity {
    private String[] data={"团队：爆破大队","客服电话：400-123-456"
            ,"客服微信：shiquww","在线网址：www.shiqu.com"};
    private ArrayAdapter<String> adapter;
    private ListView Lv;
    private TextView TvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_about);

        getViews();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        Lv.setAdapter(adapter);

        TvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getViews() {
        TvBack = (TextView)findViewById(R.id.Tv_concern_fanhui);
        Lv = (ListView)findViewById(R.id.Lv_about);
    }
}
