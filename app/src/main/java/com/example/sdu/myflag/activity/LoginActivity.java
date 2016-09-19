package com.example.sdu.myflag.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.base.BaseApplication;
import com.example.sdu.myflag.util.BaseTools;
import com.example.sdu.myflag.util.NetUtil;
import com.example.sdu.myflag.util.NetUtil.*;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/17.
 */
public class LoginActivity extends BaseActivity {
    private EditText accountEditText, passwordEditText;
    private Button forgetButton;
    private String account, password;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        //获取各组件id
        accountEditText = (EditText) findViewById(R.id.loginAccountEditText);
        passwordEditText = (EditText) findViewById(R.id.loginPasswordEditText);
        forgetButton = (Button) findViewById(R.id.loginForgetButton);
        setButtonListener();
    }

    public void goToRegister(View v) {
        startNewActivity(RegisterActivity.class);
    }

    public void login(View v) {
        if (getText()) {

        }
    }
    List<Param> params = new LinkedList<Param>();
    params.add(new Param("phone", account));
    params.add(new Param("password", password));

    LoginResult loginResult = new LoginResult();
    try {
        NetUtil.getResult(NetUtil.loginUrl, params, loginResult);
    } catch (IOException e) {
        e.printStackTrace();
    }
    //为各个按钮设置监听器
    private void setButtonListener() {
        forgetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //获取各EditText中的值，并进行合法性校验,合法返回true
    //不合法返回false，并用Toast进行提醒
    private boolean getText() {
        account = accountEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if (!BaseTools.isNetworkAvailable(LoginActivity.this)) {
            Toast.makeText(this, "当前网络不可用！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (account.isEmpty()) {
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.isEmpty()) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private class LoginResult implements CallBackForResult {

        @Override
        public void onFailure(final IOException e) {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onSuccess(Response response) {
            if (response.isSuccessful()) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String user = jsonObject.getString("user");
                    if (user.equals("")) {
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        JSONObject userJson = new JSONObject(user);
                        SharedPreferences preferences = BaseApplication.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("uid", userJson.getInt("uid") + "").apply();
                        editor.putString("phone", userJson.getString("phone")).apply();
                        editor.putString("nickname", userJson.getString("nickname")).apply();
                        editor.putString("information", userJson.getString("information")).apply();
                        editor.putString("email", userJson.getString("email")).apply();
                        editor.putString("password", password).apply();

                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                            }
                        });
                        startNewActivity(MainActivity.class);
                        LoginActivity.this.finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
