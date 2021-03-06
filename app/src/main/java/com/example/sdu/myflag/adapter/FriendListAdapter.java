package com.example.sdu.myflag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.bean.FriendBean;

import java.util.List;

/**
 * 右边字母列表适配器
 *
 * @author liuyinjun
 * @date 2015-3-16
 */
public class FriendListAdapter extends BaseAdapter implements SectionIndexer {

    private List<FriendBean> mList = null;
    private Context mContext;
    private boolean mark[];

    public FriendListAdapter(Context mContext, List<FriendBean> list) {
        this.mContext = mContext;
        this.mList = list;
        mark = new boolean[mList.size()];
        for(int i = 0; i < mark.length; i++){
            mark[i] = false;
        }
    }

    public int getCount() {
        if (mList == null)
            return 0;
        return this.mList.size();
    }

    public FriendBean getItem(int position) {
        return mList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final FriendBean friendBean = mList.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_friendlist, null);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.letter_tv);
            viewHolder.tvName = (TextView) view.findViewById(R.id.name_tv);
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mark[position] = isChecked;
            }
        });
        viewHolder.checkBox.setChecked(mark[position]);
        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(friendBean.getFirstLetter());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        viewHolder.tvName.setText(getItem(position).getName());

        return view;

    }


    final static class ViewHolder {
        TextView tvLetter;
        TextView tvName;
        CheckBox checkBox;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return mList.get(position).getFirstLetter().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mList.get(i).getFirstLetter();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public Object[] getSections() {
        return null;
    }
}