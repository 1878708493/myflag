package com.example.sdu.myflag.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.sdu.myflag.adapter.MessageAdapter;
import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.R;

public class MyMessageActivity extends BaseActivity {
    //储存与服务器通信后得到的消息
    private int num = 0;
    private String friend[];
    private String isRead[];

    private ListView listView;
    private MessageAdapter messageAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mymessage;
    }

    //此处与服务器通信，获取信息
    private boolean getMessage() {
        return false;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        listView = (ListView) findViewById(R.id.myMessageListView);
        messageAdapter = new MessageAdapter(this);
        listView.setAdapter(messageAdapter);
        ArrayList<HashMap<String, Object>> users = new ArrayList<HashMap<String, Object>>();

        if (getMessage()) {
            for (int i = 0; i < num; i++) {
                HashMap<String, Object> user = new HashMap<String, Object>();
                user.put("img", R.drawable.myself_selected);
                user.put("message", friend[i] + "邀请你做TA的监督人");
                user.put("isRead", isRead[i]);
                user.put("img2", R.drawable.ahead_arrow);
                users.add(user);
            }
        }

        //此处根据点击位置，跳转到查看具体消息的界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
