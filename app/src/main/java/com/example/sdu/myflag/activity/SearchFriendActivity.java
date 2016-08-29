package com.example.sdu.myflag.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;

/**
 * 搜索好友界面
 */
public class SearchFriendActivity extends BaseActivity implements SearchView.OnQueryTextListener{

    @Override
    public int getLayoutId() {
        return R.layout.activity_searchfriend;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }

    public void searchFriendBack(View view){
        this.finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
