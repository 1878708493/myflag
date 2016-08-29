package com.example.sdu.myflag.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.activity.SearchFriendActivity;
import com.example.sdu.myflag.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MyFragment extends BaseFragment {

    RelativeLayout messageLayout, addFriendLayout, watchLayout, settingLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addFriendLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(SearchFriendActivity.class);
            }
        });
    }

    @Override
    protected void init() {
        addFriendLayout = (RelativeLayout) mRootView.findViewById(R.id.add_friend_layout);
    }
}
