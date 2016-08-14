package com.example.sdu.myflag.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }
}
