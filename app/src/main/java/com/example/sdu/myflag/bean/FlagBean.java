package com.example.sdu.myflag.bean;

/**
 * Created by Administrator on 2016/8/20.
 */
public class FlagBean {
    private String user_name; // 用户名
    private String flag; // flag内容
    private String time_begin; // 起始时间
    private String time_end; // 终止时间
    private String watch_name; // 监督人姓名
    private String reward; // 奖励
    private String sex; // 性别

    public FlagBean(String user_name, String flag, String time_begin, String time_end, String watch_name, String reward){
        this.user_name = user_name;
        this.flag = flag;
        this.time_begin = time_begin;
        this.time_end = time_end;
        this.watch_name = watch_name;
        this.reward = reward;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(String time_begin) {
        this.time_begin = time_begin;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWatch_name() {
        return watch_name;
    }

    public void setWatch_name(String watch_name) {
        this.watch_name = watch_name;
    }
}
