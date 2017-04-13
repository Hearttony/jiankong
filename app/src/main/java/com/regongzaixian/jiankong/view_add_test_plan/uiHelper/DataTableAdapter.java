package com.regongzaixian.jiankong.view_add_test_plan.uiHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;
import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.model.TestDataEntity;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanDetailPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 2017/4/6.
 */

public class DataTableAdapter extends BaseTableAdapter {

    private final Context context;
    private final int testPlanId;
    private List<List<String>> datas = new ArrayList<>();
    private boolean triggerRequest;
    private IViewTestPlanDetailPresenter iViewTestPlanDetailPresenter;
    private int density;
    private List<String> headers;

    public DataTableAdapter(Context context, int testPlanId) {
        this.context = context;
        this.testPlanId = testPlanId;
        density = (int) context.getResources().getDisplayMetrics().density;
        initDefault();
    }

    private void initDefault() {
        headers = new ArrayList<>();
        headers.add("");
        headers.add("");
        headers.add("");
        headers.add("");
        headers.add("");
        headers.add("");
        headers.add("");
        headers.add("");
    }

    @Override
    public int getRowCount() {
        return datas.size();
    }

    @Override
    public int getColumnCount() {
        return headers.size() - 1;
    }

    @Override
    public View getView(int row, int column, View convertView, ViewGroup parent) {
        final View view;
        switch (getItemViewType(row, column)) {
            case 0:
                view = getFirstHeader(row, column, convertView, parent);
                break;
            case 1:
                view = getHeader(row, column, convertView, parent);
                break;
            case 2:
                view = getItems(row, column, convertView, parent);
                break;
            default:
                throw new RuntimeException("wtf?");
        }

        //当拉到最后一行，并且总行数大于9条时，才去触发请求分页数据
        if (row == getRowCount() - 1 && !triggerRequest && getRowCount() > 9) {
            triggerRequest = true;
            iViewTestPlanDetailPresenter.queryTestPlanDetailDataPage(testPlanId, getRowCount());

        }

        return view;
    }

    private View getItems(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.table_item, null);
        }
        ((TextView) convertView.findViewById(R.id.table_item)).setText(datas.get(row).get(column + 1));
        return convertView;
    }

    private View getHeader(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.table_header, null);
        }
        ((TextView) convertView.findViewById(R.id.table_header)).setText(headers.get(column + 1));
        return convertView;
    }

    private View getFirstHeader(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.table_header, null);
        }
        ((TextView) convertView.findViewById(R.id.table_header)).setText(headers.get(0));
        return convertView;
    }

    @Override
    public int getHeight(int row) {
        final int height;
        if (row == -1) {
            height = 35;
        } else {
            height = 45;
        }
        return Math.round(height * density);
    }

    @Override
    public int getWidth(int column) {
        return Math.round(90 * density);
    }

    @Override
    public int getItemViewType(int row, int column) {
        final int itemViewType;
        if (row == -1 && column == -1) {
            itemViewType = 0;
        } else if (row == -1) {
            itemViewType = 1;
        } else if (column == -1) {
            itemViewType = 2;
        } else {
            itemViewType = 2;
        }
        return itemViewType;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    public void setPresenter(IViewTestPlanDetailPresenter iViewTestPlanDetailPresenter) {
        this.iViewTestPlanDetailPresenter = iViewTestPlanDetailPresenter;
    }

    public void setDatas(TestDataEntity testDataEntity) {
        this.headers.clear();
        this.headers.addAll(testDataEntity.getHeaders());
        this.datas.clear();
        this.datas.addAll(testDataEntity.getTestdata());
        notifyDataSetChanged();
    }

    public void addPageData(List<List<String>> testdata) {
        this.datas.addAll(testdata);
        notifyDataSetChanged();
        //添加完分页数据之后，将triggerRequest置为false
        this.triggerRequest = false;
    }
}
