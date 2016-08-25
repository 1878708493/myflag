package com.example.sdu.myflag.base;

import android.app.Application;

/**
 * Created by Administrator on 2016/8/25.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}
