package com.example.sdu.myflag.fragment;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.adapter.FlagListAdapter;
import com.example.sdu.myflag.base.BaseFragment;
import com.example.sdu.myflag.bean.FlagBean;

import java.util.List;

/**
 * 社区界面
 */
public class CommunityFragment extends BaseFragment {

    ListView listView;
    FlagListAdapter listAdapter;
    List<FlagBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {

    }
}
