package com.example.sdu.myflag.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.activity.EditInfoActivity;
import com.example.sdu.myflag.activity.FriendActivity;
import com.example.sdu.myflag.activity.LookInfoActivity;
import com.example.sdu.myflag.activity.MyMessageActivity;
import com.example.sdu.myflag.activity.SearchFriendActivity;
import com.example.sdu.myflag.base.BaseApplication;
import com.example.sdu.myflag.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    RelativeLayout messageLayout, addFriendLayout, watchLayout, settingLayout, userInfoLayout;
    TextView userNameTv, userIntroTv;
    ImageView userSexImg;
    LinearLayout friendLayout;
    private String nickname, information, sex;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE);
        nickname = sp.getString("nickname", "");
        information = sp.getString("information", "");
        sex = sp.getString("sex", "");
        userNameTv.setText(nickname);
        userIntroTv.setText(information);

        addFriendLayout.setOnClickListener(this);
        friendLayout.setOnClickListener(this);
        userInfoLayout.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
    }

    @Override
    protected void init() {
        addFriendLayout = (RelativeLayout) mRootView.findViewById(R.id.add_friend_layout);
        userNameTv = (TextView) mRootView.findViewById(R.id.user_name_tv);
        userIntroTv = (TextView) mRootView.findViewById(R.id.user_intro_tv);
        userSexImg = (ImageView) mRootView.findViewById(R.id.user_sex_img);
        friendLayout = (LinearLayout) mRootView.findViewById(R.id.friend_layout);
        userInfoLayout = (RelativeLayout) mRootView.findViewById(R.id.user_info_layout);
        messageLayout = (RelativeLayout) mRootView.findViewById(R.id.message_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.friend_layout:
                startNewActivity(FriendActivity.class);
                break;

            case R.id.add_friend_layout:
                startNewActivity(SearchFriendActivity.class);
                break;

            case R.id.user_info_layout:
                Intent intent = new Intent(MyFragment.this.getActivity(), LookInfoActivity.class);
                intent.putExtra("nickname", nickname);
                intent.putExtra("sex", sex);
                intent.putExtra("info", information);
                startActivityForResult(intent, 0);
                break;

            case R.id.message_layout:
                startNewActivity(MyMessageActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
