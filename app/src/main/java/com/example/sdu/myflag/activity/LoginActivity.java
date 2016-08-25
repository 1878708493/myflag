package com.example.sdu.myflag.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;
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
public class LoginActivity extends BaseActivity
{
    private EditText accountEditText,passwordEditText;
    private Button loginButton,registerButton,forgetButton;
    private String account,password;
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState)
    {
        //获取各组件id
        accountEditText = (EditText) findViewById(R.id.loginAccountEditText);
        passwordEditText = (EditText) findViewById(R.id.loginPasswordEditText);
        loginButton = (Button)findViewById(R.id.loginLoginButton);
        registerButton = (Button)findViewById(R.id.loginRegisterButton);
        forgetButton = (Button)findViewById(R.id.loginForgetButton);
        setButtonListener();
    }

    public void goToRegister(View v){
        startNewActivity(RegisterActivity.class);
    }

    public void login(View v){
        startNewActivity(MainActivity.class);
    }


    //为各个按钮设置监听器
    private void setButtonListener()
    {
        loginButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(getText())
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run()
                        {
                            List<Param> params = new LinkedList<Param>();
                            params.add(new Param("id",account));
                            params.add(new Param("password",password));
                            String url = "http://119.29.236.181/myflag/user/Login";
                            LoginResult loginResult = new LoginResult();
                            try {
                                NetUtil.getResult(url,params,loginResult);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }

            }
        });

        registerButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        forgetButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });


    }

    //获取各EditText中的值，并进行合法性校验,合法返回true
    //不合法返回false，并用Toast进行提醒
    private boolean getText()
    {
        boolean legal = true;
        String str="";
        account = accountEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if(account.isEmpty())
        {
            str="账户不能为空！";
            legal = false;
        }
        if(password.isEmpty())
        {
            str="密码不能为空！";
            legal = false;
        }

        if(!legal)
        {
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private class LoginResult implements CallBackForResult
    {

        @Override
        public void onFailure(IOException e)
        {
            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(Response response)
        {
            if(response.isSuccessful())
            {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());


                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();

                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
