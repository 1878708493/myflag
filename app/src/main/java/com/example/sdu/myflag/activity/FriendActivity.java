package com.example.sdu.myflag.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.adapter.FriendListAdapter;
import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.bean.FriendBean;
import com.example.sdu.myflag.util.CharacterParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 好友列表界面
 */
public class FriendActivity extends BaseActivity {

    private ListView listView;
    private List<FriendBean> list;
    private FriendListAdapter friendListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_friend;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        listView = (ListView) findViewById(R.id.listView);
        list = fillData(getResources().getStringArray(R.array.list));
        friendListAdapter = new FriendListAdapter(this, list);
        listView.setAdapter(friendListAdapter);
    }

    private List<FriendBean> fillData(String[] array) {
        List<FriendBean> newList = new ArrayList<>();
        CharacterParser characterParser = new CharacterParser();

        for (int i = 0; i < array.length; i++) {
            FriendBean friendBean = new FriendBean();
            friendBean.setName(array[i]);

            String pinYin = characterParser.getSelling(array[i]);
            String sortString = pinYin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                friendBean.setFirstLetter(sortString.toUpperCase());
            } else {
                friendBean.setFirstLetter("#");
            }
            newList.add(friendBean);
        }
        Collections.sort(newList);
        return newList;
    }
}
