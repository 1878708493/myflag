package com.example.sdu.myflag.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.R;
import com.example.sdu.myflag.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Response;


public class SearchFriendActivity extends BaseActivity
{
    private SearchView sv;
    @Override
    public int getLayoutId() {
        return R.layout.activity_searchfriend;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState)
    {
        sv=(SearchView)findViewById(R.id.searchView);
        sv.setSubmitButtonEnabled(true);
        sv.setQueryHint("搜索手机/ID号");
        sv.setOnQueryTextListener(new OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query)
            {
                searchFriend(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    private void searchFriend(String str)
    {
        SearchFriendResult searchFriendResult = new SearchFriendResult();
        List<NetUtil.Param> params = new LinkedList<NetUtil.Param>();

        if(str.length()==11)//手机号查找
        {
            params.add(new NetUtil.Param("phone", str));
        }
        else//id查找
        {
            params.add(new NetUtil.Param("id", str));
        }

        try {
            NetUtil.getResult(NetUtil.findUserUrl, params, searchFriendResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SearchFriendResult implements NetUtil.CallBackForResult
    {

        @Override
        public void onFailure(final IOException e)
        {
            SearchFriendActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SearchFriendActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onSuccess(Response response)
        {
            if(response.isSuccessful())
            {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String user = null;
                    user = jsonObject.getString("user");
                    if (user.equals("")) {
                        SearchFriendActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SearchFriendActivity.this, "查找失败", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                    {
                        //查找成功，跳转到添加界面
                        //json解析在添加界面进行
                        Intent intent = new Intent();
                        intent.setClass(SearchFriendActivity.this,AddFriendActivity.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        SearchFriendActivity.this.finish();
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