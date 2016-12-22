package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ui.Urls;

/**
 * Created by lijing on 16/11/29.
 */
public class SearchTimeActivity extends Activity implements ZJBCircleSeekBar.OnCircleSeekBarChangeListener {
    private ZJBCircleSeekBar circleSeekBar;
    TextView mTextView;
    private ImageView back;
    private Button mBtnSearch;
    private ImageView mIvSearch;
    private TextView mEtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_foodtime);

        findId();
        setListener();
    }

    private void setListener() {
        back.setOnClickListener(myListener);
        mIvSearch.setOnClickListener(myListener);
        mBtnSearch.setOnClickListener(myListener);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.search_time_btn:
                    mEtSearch.setText(mTextView.getText().toString() + "分钟");
                    break;
                case R.id.search_foodtime_Iv:
//                    Intent intent = new Intent(SearchTimeActivity.this,SearchListTimeActivity.class);
                    Intent intent = new Intent(SearchTimeActivity.this,SearchListActivity.class);
                    intent.putExtra("title",mEtSearch.getText().toString());
                    intent.putExtra("Url", Urls.urlSearchTime);
                    intent.putExtra("flag","2");
                    startActivity(intent);
                    break;
                case R.id.Iv_search_back:
                    finish();
                    Intent i = new Intent(SearchTimeActivity.this, MainActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };
    
    private void findId() {
        back = (ImageView) findViewById(R.id.Iv_search_back);
        circleSeekBar = (ZJBCircleSeekBar) findViewById(R.id.circleSeekBar);
        mTextView = (TextView) findViewById(R.id.tv_SearchTime_time);
        circleSeekBar.setOnSeekBarChangeListener(this);
        mEtSearch = (EditText) findViewById(R.id.Et_search_time);
        mIvSearch = (ImageView)findViewById(R.id.search_foodtime_Iv);
        mBtnSearch = (Button)findViewById(R.id.search_time_btn);
    }
    @Override
    public void onProgressChanged(ZJBCircleSeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub
        mTextView.setText(progress + "");
    }
}
