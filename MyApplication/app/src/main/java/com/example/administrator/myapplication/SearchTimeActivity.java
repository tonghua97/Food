package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lijing on 16/11/29.
 */
public class SearchTimeActivity extends Activity implements ZJBCircleSeekBar.OnCircleSeekBarChangeListener {
    private ZJBCircleSeekBar circleSeekBar;
    TextView mTextView;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_foodtime);
        circleSeekBar = (ZJBCircleSeekBar) findViewById(R.id.circleSeekBar);
        mTextView = (TextView) findViewById(R.id.tv_SearchTime_time);
        circleSeekBar.setOnSeekBarChangeListener(this);

        findId();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(SearchTimeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void findId() {
        back = (ImageView) findViewById(R.id.Iv_search_back);
    }
    @Override
    public void onProgressChanged(ZJBCircleSeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub
        mTextView.setText(progress + "");
    }
}
