package com.example.sdu.myflag.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.base.BaseApplication;
import com.example.sdu.myflag.util.NetUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import carbon.widget.RadioButton;
import carbon.widget.RadioGroup;
import okhttp3.Response;

/**
 * 新建FLAG界面
 */
public class CreateFlagActivity extends BaseActivity {
    EditText contentEditText, formEditText, inviteEditText, limitEditText, awardEditText;
    Button commitButton;
    String content = "", form = "", beginTime = "", endTime = "", invite = "", limit = "", award = "", id = "";
    String isTeam;
    RadioButton personalRb, teamRb;
    RadioGroup radioGroup;
    TextView beginTv, endTv;
    int beginYear, beginMonth, beginDay, endYear, endMonth, endDay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_flag;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        init();
        setListener();
    }

    private void init() {
        contentEditText = (EditText) findViewById(R.id.newFlagContentEditText);
        formEditText = (EditText) findViewById(R.id.newFlagFormEditText);
        beginTv = (TextView) findViewById(R.id.begin_tv);
        endTv = (TextView) findViewById(R.id.end_tv);
        inviteEditText = (EditText) findViewById(R.id.newFlagInviteEditText);
        limitEditText = (EditText) findViewById(R.id.newFlagLimitEditText);
        awardEditText = (EditText) findViewById(R.id.newFlagAwardEditText);
        commitButton = (Button) findViewById(R.id.newFlagCommitButton);

        personalRb = (RadioButton) findViewById(R.id.personal_rb);
        teamRb = (RadioButton) findViewById(R.id.team_rb);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        isTeam = "false";
    }

    private void setListener() {

        beginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View calendar_view = View.inflate(CreateFlagActivity.this, R.layout.date_picker, null);
                final DatePicker datePicker = (DatePicker) calendar_view.findViewById(R.id.date_picker);
                int y, m, d;
                if (beginTv.getText().toString().isEmpty()) {
                    Calendar calendar = Calendar.getInstance();
                    y = calendar.get(Calendar.YEAR);
                    m = calendar.get(Calendar.MONTH);
                    d = calendar.get(Calendar.DAY_OF_MONTH);
                } else {
                    y = beginYear;
                    m = beginMonth;
                    d = beginDay;
                }
                datePicker.init(y, m, d, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateFlagActivity.this);
                builder.setView(calendar_view);
                builder.setTitle("选择时间");
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        beginYear = datePicker.getYear();
                        beginMonth = datePicker.getMonth();
                        beginDay = datePicker.getDayOfMonth();
                        beginTv.setText(beginYear + "年" + (beginMonth + 1) + "月" + beginDay + "日");
                    }
                }).show();
            }
        });

        endTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View calendar_view = View.inflate(CreateFlagActivity.this, R.layout.date_picker, null);
                final DatePicker datePicker = (DatePicker) calendar_view.findViewById(R.id.date_picker);
                int y, m, d;
                if (beginTv.getText().toString().isEmpty()) {
                    Calendar calendar = Calendar.getInstance();
                    y = calendar.get(Calendar.YEAR);
                    m = calendar.get(Calendar.MONTH);
                    d = calendar.get(Calendar.DAY_OF_MONTH);
                } else {
                    y = endYear;
                    m = endMonth;
                    d = endDay;
                }
                datePicker.init(y, m, d, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateFlagActivity.this);
                builder.setView(calendar_view);
                builder.setTitle("选择时间");
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        endYear = datePicker.getYear();
                        endMonth = datePicker.getMonth();
                        endDay = datePicker.getDayOfMonth();
                        endTv.setText(endYear + "年" + (endMonth + 1) + "月" + endDay + "日");
                    }
                }).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == teamRb.getId()) {
                    isTeam = "true";
                    formEditText.setVisibility(View.VISIBLE);
                } else {
                    isTeam = "false";
                    formEditText.setVisibility(View.GONE);
                }
            }
        });

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getText()) {
                    List<NetUtil.Param> params = new LinkedList<NetUtil.Param>();
                    params.add(new NetUtil.Param("id", id));
                    params.add(new NetUtil.Param("content", content));
                    params.add(new NetUtil.Param("award", award));
                    params.add(new NetUtil.Param("isTeam", isTeam));
                    params.add(new NetUtil.Param("startTime", beginTime));
                    params.add(new NetUtil.Param("endTime", endTime));
                    params.add(new NetUtil.Param("supervise", invite));
                    params.add(new NetUtil.Param("member", form));

                    CreateFlagResult createFlagResult = new CreateFlagResult();
                    try {
                        NetUtil.getResult(NetUtil.createFlagUrl, params, createFlagResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean getText() {
        content = contentEditText.getText().toString();
        form = formEditText.getText().toString();
        beginTime = beginTv.getText().toString();
        endTime = endTv.getText().toString();
        invite = inviteEditText.getText().toString();
        limit = limitEditText.getText().toString();
        award = awardEditText.getText().toString();

        SharedPreferences sharedPreferences = BaseApplication.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", null);

        if (id == null) {
            Toast.makeText(this, "获取用户ID失败！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (content.isEmpty() || form.isEmpty() || beginTime.isEmpty() || endTime.isEmpty() || invite.isEmpty() || limit.isEmpty() || award.isEmpty()) {
            Toast.makeText(this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!convertTime()) {
            Toast.makeText(this, "时间输入不合法！", Toast.LENGTH_SHORT).show();
            return false;
        }
        form.replaceAll("@", "#");
        invite.replaceAll("@", "#");

        return true;
    }

    private boolean convertTime() {
        String t1 = "", t2 = "";

        for (int i = 0; i < beginTime.length(); i++) {
            char c = beginTime.charAt(i);
            if (c >= '0' && c <= 9) {
                t1 += c;
            } else {
                t1 += "-";
            }
        }

        for (int i = 0; i < endTime.length(); i++) {
            char c = endTime.charAt(i);
            if (c >= '0' && c <= 9) {
                t2 += c;
            } else {
                t2 += "-";
            }
        }

        t1.replaceAll("--", "-");
        t2.replaceAll("--", "-");
        t1.replaceAll(" ", "");
        t2.replaceAll(" ", "");

        if (t1.charAt(6) == '-') {
            t1 = t1.substring(0, 5) + "0" + t1.substring(5);
        }

        if (t1.length() < 10) {
            t1 = t1.substring(0, 9) + "0" + t1.substring(9);
        }

        if (t2.charAt(6) == '-') {
            t2 = t2.substring(0, 5) + "0" + t2.substring(5);
        }

        if (t2.length() < 10) {
            t2 = t2.substring(0, 9) + "0" + t2.substring(9);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(t1);
            Date date2 = sdf.parse(t2);
            long a = date1.getTime() / 1000;
            long b = date2.getTime() / 1000;
            t1 = Long.toString(a);
            t2 = Long.toString(b);
            t1 = t1.substring(t1.length() - 10);
            t2 = t2.substring(t1.length() - 10);

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        beginTime = t1;
        endTime = t2;
        return true;
    }

    public void create_backTo(View view) {
        this.finish();
    }

    private class CreateFlagResult implements NetUtil.CallBackForResult {
        @Override
        public void onFailure(final IOException e) {
            CreateFlagActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CreateFlagActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onSuccess(Response response) {
            if (response.isSuccessful()) {
                try {
                    String result = response.body().string();
                    final String str;

                    if (result.equals("0"))
                        str = "创建失败";
                    else
                        str = "创建成功";

                    CreateFlagActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CreateFlagActivity.this, str, Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
