package com.example.sdu.myflag.activity;

import android.content.Intent;
import android.database.CursorJoiner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.R;

public class AddFriendMessageActivity extends BaseActivity
{
    private Button cancel,next;
    private EditText editText;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_addfriendmessage;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState)
    {
        cancel =(Button)findViewById(R.id.messageCancelButton);
        next =(Button)findViewById(R.id.messageNextButton);
        editText=(EditText) findViewById(R.id.messageEditText);

        intent = this.getIntent();

        setListener();
    }

    private void setListener()
    {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AddFriendMessageActivity.this.setResult(RESULT_CANCELED,intent);
                AddFriendMessageActivity.this.finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String message = editText.getText().toString();
                intent.putExtra("message",message);
                AddFriendMessageActivity.this.setResult(RESULT_OK,intent);
                AddFriendMessageActivity.this.finish();
            }
        });
    }
}
