package com.example.sdu.myflag.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.base.BaseActivity;

/**
 * 查看个人信息
 */
public class LookInfoActivity extends BaseActivity {

    private TextView nickNameTv, infoTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lookinfo;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        nickNameTv = (TextView) findViewById(R.id.nick_name_tv);
        infoTv = (TextView) findViewById(R.id.info_tv);
    }

    public void editInfo(View view) {
        Intent intent = new Intent(LookInfoActivity.this, EditInfoActivity.class);
        intent.putExtra("nickname", nickNameTv.getText());
        intent.putExtra("info", infoTv.getText());
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case 1:
                String nickname = data.getStringExtra("nickname");
                String info = data.getStringExtra("information");
                nickNameTv.setText(nickname);
                infoTv.setText(info);
                break;
        }
    }
}
