package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lijing on 16/11/29.
 */
public class SearchNameActivity extends Activity {

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_foodname);

        findId();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchNameActivity.this,MainActivity.class);
            }
        });
    }

    private void findId() {
        back = (ImageView) findViewById(R.id.Iv_search_back);
    }
}
