package com.example.sdu.myflag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.sdu.myflag.R;

/**
 * Created by Administrator on 2016/9/5.
 */
public class MessageAdapter extends BaseAdapter {

    Context context;
    public MessageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_mymessage, null);
        return convertView;
    }
}
