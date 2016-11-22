package com.example.administrator.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;

import com.example.administrator.ui.fragment_pickfun;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    private LinearLayout Activity_main;
    private fragment_pickfun mPickfun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultPage();
        Activity_main.invalidate();
    }

    private void setDefaultPage() {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        mPickfun = new fragment_pickfun();
        transaction.replace(R.id.container,mPickfun);
        transaction.commit();
        Activity_main.invalidate();
    }
}
