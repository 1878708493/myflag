package com.example.sdu.myflag.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.adapter.FlagListAdapter;
import com.example.sdu.myflag.base.BaseApplication;
import com.example.sdu.myflag.base.BaseFragment;
import com.example.sdu.myflag.bean.FlagBean;
import com.example.sdu.myflag.util.BaseTools;
import com.example.sdu.myflag.util.NetUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/17.
 */
public class MainFragment extends BaseFragment {

    ListView listView;
    List<FlagBean> mList;
    FlagListAdapter listAdapter;
    LinearLayout emptyLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        if(BaseTools.isNetworkAvailable(this.getActivity())){
            getFlag();
        }
        else{
            emptyLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void init() {
        listView = (ListView) mRootView.findViewById(R.id.fragment_main_flag_lv);
        emptyLayout = (LinearLayout) mRootView.findViewById(R.id.empty_layout);
        mList = new ArrayList<>();/*
        mList.add(new FlagBean("qwer", "减肥", "2016年12月13日", "2016年12月30日", "王二", "晚餐"));
        mList.add(new FlagBean("qwer", "减肥", "2016年8月3日", "2016年8月30日", "王二", "晚餐"));
        mList.add(new FlagBean("qwer", "减肥", "2016年8月3日", "2016年8月30日", "王二", "晚餐"));
        mList.add(new FlagBean("qwer", "减肥", "2016年8月3日", "2016年8月30日", "王二", "晚餐"));*/
        listAdapter = new FlagListAdapter(this.getContext(), mList);
    }

    private void getFlag() {
        SharedPreferences sharedPreferences = BaseApplication.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("uid", null);

        if (id == null) {
            Toast.makeText(this.getActivity(), "获取用户ID失败！", Toast.LENGTH_SHORT).show();
            return;
        }

        Date date = new Date();
        long t = date.getTime() / 1000;
        String time = Long.toString(t);
        time = time.substring(time.length() - 10);

        GetFlagResult getFlagResult = new GetFlagResult();

        List<NetUtil.Param> params = new LinkedList<NetUtil.Param>();
        params.add(new NetUtil.Param("id", id));
        params.add(new NetUtil.Param("time", time));

        try {
            NetUtil.getResult(NetUtil.getMyFlagUrl, params, getFlagResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class GetFlagResult implements NetUtil.CallBackForResult {
        @Override
        public void onFailure(final IOException e) {
            MainFragment.this.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainFragment.this.getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onSuccess(Response response) {
            if (response.isSuccessful()) {
                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    int size = jsonArray.length();
                    String content[] = new String[size];
                    String award[] = new String[size];
                    int achieve[] = new int[size];
                    int fid[] = new int[size];
                    int id[] = new int[size];
                    long startTime[] = new long[size];
                    long endTime[] = new long[size];
                    long createTime[] = new long[size];

                    for (int i = 0; i < size; i++) {
                        JSONObject js = jsonArray.getJSONObject(i).getJSONObject("flag");

                        content[i] = js.optString("content");
                        award[i] = js.optString("award");
                        achieve[i] = js.getInt("achieve");
                        fid[i] = js.getInt("fid");
                        id[i] = js.getInt("id");
                        startTime[i] = js.optLong("startTime");
                        endTime[i] = js.optLong("endTime");
                        createTime[i] = js.optLong("createTime");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
