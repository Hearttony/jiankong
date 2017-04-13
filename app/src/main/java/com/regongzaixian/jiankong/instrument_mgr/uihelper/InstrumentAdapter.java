package com.regongzaixian.jiankong.instrument_mgr.uihelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.model.InstrumentEntity;
import com.regongzaixian.jiankong.instrument_mgr.frame.IInstrumentMgrPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:34
 * Description:
 */
public class InstrumentAdapter extends BaseAdapter {
    private final Context context;
    private List<InstrumentEntity> datas = new ArrayList<>();
    private IInstrumentMgrPresenter iInstrumentMgrPresenter;

    public InstrumentAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<InstrumentEntity> devices) {
        this.datas = devices;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public InstrumentEntity getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return datas.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_device_instrument, null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_device_instrument_title);
            viewHolder.chooseImg = (CheckBox) convertView.findViewById(R.id.item_device_instrument_choose_img);
            viewHolder.checkImg = (ImageView) convertView.findViewById(R.id.item_device_instrument_check_img);
            viewHolder.divider = (ImageView) convertView.findViewById(R.id.item_divider_line);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(datas.get(position).getName());

        viewHolder.checkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iInstrumentMgrPresenter.gotoModifyDetail(getItem(position));
            }
        });

        viewHolder.chooseImg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    select(position);
                } else {
                    getItem(position).setSelect(false);
                    notifyDataSetChanged();
                }
            }
        });

        if (getItem(position).isSelect()) {
            viewHolder.chooseImg.setChecked(true);
        } else {
            viewHolder.chooseImg.setChecked(false);
        }

        return convertView;
    }

    private void select(int position) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isSelect()) {
                datas.get(i).setSelect(false);
            }
        }
        datas.get(position).setSelect(true);
        notifyDataSetChanged();
    }

    public InstrumentEntity getSelectedData() {
        for (InstrumentEntity data : datas) {
            if (data.isSelect()) {
                return data;
            }
        }
        return null;
    }

    public List<InstrumentEntity> getDatas() {
        return datas;
    }

    public void setPresenter(IInstrumentMgrPresenter iInstrumentMgrPresenter) {
        this.iInstrumentMgrPresenter = iInstrumentMgrPresenter;
    }
}
