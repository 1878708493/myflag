package com.example.sdu.myflag.activity;

import android.os.Bundle;
import android.view.View;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;

/**
 * Created by Administrator on 2016/8/17.
 */
public class LoginActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }

    public void goToRegister(View v){
        startNewActivity(RegisterActivity.class);
    }

    public void login(View v){
        startNewActivity(MainActivity.class);
    }
}
