package com.regongzaixian.jiankong.util.materialdialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lingshikeji.xjapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:40
 * Description:
 */
public class DialogListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> lists = new ArrayList<>();
    private int selectPosition;

    public DialogListViewAdapter(Context mContext, List<String> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }

    public void refreshAdapter(List<String> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public void refreshAdapter() {
        notifyDataSetChanged();
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    @Override
    public int getCount() {
        return lists.size();
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.dialog_listview_item_layout, null);
            viewHolder.txt = (TextView) view.findViewById(R.id.storage_list_item_text);
            viewHolder.img = (ImageView) view.findViewById(R.id.storage_list_item_image);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txt.setText(lists.get(position));
        if (position == selectPosition) {
            viewHolder.img.setImageResource(R.drawable.icon_dialog_radio_on);
        } else {
            viewHolder.img.setImageResource(R.drawable.icon_dialog_radio_off);
        }

        return view;
    }

    public class ViewHolder {
        private TextView txt;
        private ImageView img;

    }
}
