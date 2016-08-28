package com.example.sdu.myflag.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseApplication;
import com.example.sdu.myflag.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class AddFriendActivity extends BaseActivity {
    private String user,friendId,friendName,friendInformation;
    private Button messageButton,sendButton;
    private EditText remarkEditText;
    private TextView nameTextView,informationTextView;
    private JSONObject userJson;
    @Override
    public int getLayoutId() {
        return R.layout.activity_addfriend;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState)
    {
        user=savedInstanceState.getString("user");
        messageButton = (Button)findViewById(R.id.addFriendMessageButton);
        sendButton = (Button)findViewById(R.id.addFriendSendButton);
        nameTextView = (TextView) findViewById(R.id.addFriendNameTextView);
        informationTextView = (TextView) findViewById(R.id.addFriendInformationTextView);
        remarkEditText = (EditText) findViewById(R.id.addFriendRemarkEditText);

        getJson();
        setListener();
    }

    private void getJson()
    {
        try {
            userJson = new JSONObject(user);
            friendId = userJson.optString("uid",null);
            friendName = userJson.optString("nickname",null);
            friendInformation = userJson.optString("information",null);

            if(friendName!=null)
            {
                nameTextView.setText(friendName);
            }

            if(friendInformation!=null)
            {
                informationTextView.setText(friendInformation);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setListener()
    {
        //此处未完成
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String remark = remarkEditText.getText().toString();
                String message = "";//验证信息此处未完成
                AddFriendResult addFriendResult = new AddFriendResult();
                List<NetUtil.Param> params = new ArrayList<>();

                SharedPreferences sharedPreferences = BaseApplication.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE);
                String requestId = sharedPreferences.getString("uid",null);
                if(requestId==null)
                {
                    Toast.makeText(AddFriendActivity.this, "获取用户ID失败！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(friendId==null)
                {
                    Toast.makeText(AddFriendActivity.this, "获取好友ID失败！", Toast.LENGTH_SHORT).show();
                    return;
                }

                params.add(new NetUtil.Param("id",friendId));
                params.add(new NetUtil.Param("requestId",requestId));
                params.add(new NetUtil.Param("message",message));
                params.add(new NetUtil.Param("remark",remark));

                try {
                    NetUtil.getResult(NetUtil.addFriendUrl, params, addFriendResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private class AddFriendResult implements NetUtil.CallBackForResult
    {
        @Override
        public void onFailure(final IOException e)
        {
            AddFriendActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(AddFriendActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onSuccess(Response response)
        {
            if(response.isSuccessful())
            {
                AddFriendActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddFriendActivity.this,"请求发送成功", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}
