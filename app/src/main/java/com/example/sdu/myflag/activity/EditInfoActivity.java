package com.example.sdu.myflag.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.base.BaseApplication;
import com.example.sdu.myflag.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import carbon.widget.RadioButton;
import carbon.widget.RadioGroup;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/3.
 */
public class EditInfoActivity extends BaseActivity {

    private EditText nickNameEdt, signatureEdt;
    private String nickName, signature, sex;
    private SharedPreferences sharedPreferences;
    private RadioButton male, female;
    private RadioGroup radioGroup;

    @Override
    public int getLayoutId() {
        return R.layout.activity_editinfo;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        Intent intent = this.getIntent();
        nickName = intent.getStringExtra("nickname");
        signature = intent.getStringExtra("info");
        sex = "";

        nickNameEdt = (EditText) findViewById(R.id.nick_name_edt);
        signatureEdt = (EditText) findViewById(R.id.signature_edt);
        radioGroup = (RadioGroup) findViewById(R.id.sex_radio_group);
        male = (RadioButton) findViewById(R.id.male_rb);
        female = (RadioButton) findViewById(R.id.female_rb);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == male.getId()){
                    sex = "男";
                }
                else if(checkedId == female.getId()){
                    sex = "女";
                }
            }
        });
        nickNameEdt.setText(nickName);
        signatureEdt.setText(signature);
    }

    public void editInfoPost(View view) {
        if (nickNameEdt.getText().toString() == null || nickNameEdt.getText().toString().length() == 0) {
            Toast.makeText(EditInfoActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(sex.equals("")){
            Toast.makeText(EditInfoActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        nickName = nickNameEdt.getText().toString();
        signature = signatureEdt.getText().toString();

        List<NetUtil.Param> params = new ArrayList<>();
        sharedPreferences = BaseApplication.getInstance().getSharedPreferences("User", Context.MODE_PRIVATE);
        params.add(new NetUtil.Param("id", sharedPreferences.getString("uid", "")));
        params.add(new NetUtil.Param("nickname", nickName));
        params.add(new NetUtil.Param("information", signature));
        try {
            NetUtil.getResult(NetUtil.editInfoUrl, params, new EditInfoCallBack(nickName, signature));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class EditInfoCallBack implements NetUtil.CallBackForResult {

        private String nickname;
        private String info;

        public EditInfoCallBack(String nickname, String info) {
            this.nickname = nickname;
            this.info = info;
        }

        @Override
        public void onFailure(final IOException e) {
            EditInfoActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(EditInfoActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onSuccess(Response response) {
            if (response.isSuccessful()) {
                try {
                    String res = response.body().string();
                    if (res.equals("1")) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nickname", nickname);
                        editor.putString("information", info);
                        EditInfoActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EditInfoActivity.this, "修改信息成功", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent();
                                intent.putExtra("nickname", nickName);
                                intent.putExtra("information", info);
                                EditInfoActivity.this.setResult(1, intent);
                                EditInfoActivity.this.finish();
                            }
                        });
                    } else if(res.equals("0")) {
                        EditInfoActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(EditInfoActivity.this, "修改信息失败", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void editInfoBackTo(View view) {
        setResult(0);
        this.finish();
    }
}
