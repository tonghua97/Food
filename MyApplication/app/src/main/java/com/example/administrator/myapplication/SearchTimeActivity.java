package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.ui.CircularTimePicker;
import com.example.administrator.ui.Urls;

/**
 * Created by lijing on 16/11/29.
 */
public class SearchTimeActivity extends Activity {
    private ImageView back;
    private Button mBtnSearch;
    private TextView mEtSearch;
    private RelativeLayout rl;
    private ImageView mIvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_foodtime);

        findId();
        setListener();
        initTimePicker();
    }

    private void setListener() {
        back.setOnClickListener(myListener);
        mBtnSearch.setOnClickListener(myListener);
        mIvSearch.setOnClickListener(myListener);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.search_time_btn:
                    String search = mEtSearch.getText().toString();
                    Intent i = new Intent(SearchTimeActivity.this, SearchListActivity.class);
                    i.putExtra("title",search);
                    i.putExtra("Url", Urls.urlSearchTime);
                    i.putExtra("flag","2");
                    startActivity(i);
                    break;
                case R.id.Iv_search_back:
                    finish();
                    break;
                case R.id.search_foodtime_Iv:
                    Intent intent = new Intent(SearchTimeActivity.this, SearchListActivity.class);
                    intent.putExtra("title",mEtSearch.getText().toString());
                    intent.putExtra("Url", Urls.urlSearchTime);
                    intent.putExtra("flag","2");
                    startActivity(intent);
                    break;
            }
        }
    };
    
    private void findId() {
        back = (ImageView) findViewById(R.id.Iv_search_back);
        rl = (RelativeLayout) findViewById(R.id.RL_time);
        mEtSearch = (EditText) findViewById(R.id.Et_search_time);
        mBtnSearch = (Button)findViewById(R.id.search_time_btn);
        mIvSearch = (ImageView)findViewById(R.id.search_foodtime_Iv);
    }
    private void initTimePicker(){
        CircularTimePicker timePicker = new CircularTimePicker(getApplicationContext(),R.drawable.time_point, CircularTimePicker.ViewType.TimePiker);
        rl.addView(timePicker);
        timePicker.setSeekChangeListener(new CircularTimePicker.OnSeekChangeListener() {
            @Override
            public void onProgressChange(CircularTimePicker view, int newProgress) {
                mEtSearch.setText(newProgress+"分钟");
            }
        });
        timePicker.setMaxProgress(120);
    }
}
