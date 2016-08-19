package com.example.sdu.myflag.activity;

import android.os.Bundle;
import android.view.View;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;

/**
 * Created by Administrator on 2016/8/17.
 */
public class RegisterActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }

    public void backTo(View v){
        RegisterActivity.this.finish();
    }
}
