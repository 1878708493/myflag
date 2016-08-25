package com.example.sdu.myflag.activity;

import android.os.Bundle;
import android.view.View;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;

/**
 * 新建FLAG界面
 */
public class CreateFlagActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_flag;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }

    public void create_backTo(View view){
        this.finish();
    }
}
