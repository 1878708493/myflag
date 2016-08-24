package com.example.sdu.myflag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.bean.FlagBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/20.
 */
public class FlagListAdapter extends BaseAdapter{

    private List<FlagBean> mList;
    private Context context;

    public FlagListAdapter(Context context, List<FlagBean> list){
        this.mList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(mList == null)
            return null;
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_flag, null);
            viewHolder.flag = (TextView) convertView.findViewById(R.id.flag_tv);
            viewHolder.reward = (TextView) convertView.findViewById(R.id.reward_tv);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time_tv);
            viewHolder.watch = (TextView) convertView.findViewById(R.id.watch_tv);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.flag.setText(mList.get(position).getFlag());
        viewHolder.watch.setText(mList.get(position).getWatch_name());
        viewHolder.time.setText(mList.get(position).getTime_begin() + '-' + mList.get(position).getTime_end());
        viewHolder.reward.setText(mList.get(position).getReward());

        return convertView;
    }

    class ViewHolder{
        public TextView flag;
        public TextView time;
        public TextView watch;
        public TextView reward;
    }
}
