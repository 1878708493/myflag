package com.example.sdu.myflag.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.adapter.FlagListAdapter;
import com.example.sdu.myflag.base.BaseFragment;
import com.example.sdu.myflag.bean.FlagBean;
import com.example.sdu.myflag.util.NetUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/17.
 */
public class MainFragment extends BaseFragment {

    ListView listView;
    List<FlagBean> mList;
    FlagListAdapter listAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void init() {
        listView = (ListView) mRootView.findViewById(R.id.fragment_main_flag_lv);
        mList = new ArrayList<>();
        mList.add(new FlagBean("qwer", "减肥", "2016年8月3日", "2016年8月30日", "王二", "晚餐"));
        mList.add(new FlagBean("qwer", "减肥", "2016年8月3日", "2016年8月30日", "王二", "晚餐"));
        mList.add(new FlagBean("qwer", "减肥", "2016年8月3日", "2016年8月30日", "王二", "晚餐"));
        mList.add(new FlagBean("qwer", "减肥", "2016年8月3日", "2016年8月30日", "王二", "晚餐"));
        listAdapter = new FlagListAdapter(this.getContext(), mList);
    }
}
