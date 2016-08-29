package com.example.sdu.myflag.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.activity.SearchFriendActivity;
import com.example.sdu.myflag.base.BaseApplication;
import com.example.sdu.myflag.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MyFragment extends BaseFragment {

    RelativeLayout messageLayout, addFriendLayout, watchLayout, settingLayout;
    TextView userNameTv, userIntroTv;
    ImageView userSexImg;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE);
        String nickname = sp.getString("nickname", "");
        String information = sp.getString("information", "");
        userNameTv.setText(nickname);
        userIntroTv.setText(information);
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
        userNameTv = (TextView) mRootView.findViewById(R.id.user_name_tv);
        userIntroTv = (TextView) mRootView.findViewById(R.id.user_intro_tv);
        userSexImg = (ImageView) mRootView.findViewById(R.id.user_sex_img);
    }
}
