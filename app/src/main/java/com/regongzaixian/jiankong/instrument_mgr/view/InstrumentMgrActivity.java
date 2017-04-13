package com.regongzaixian.jiankong.instrument_mgr.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.base.BaseActivity;
import com.regongzaixian.jiankong.model.InstrumentEntity;
import com.regongzaixian.jiankong.instrument_mgr.frame.IInstrumentMgrPresenter;
import com.regongzaixian.jiankong.instrument_mgr.frame.IInstrumentMgrView;
import com.regongzaixian.jiankong.instrument_mgr.presenter.InstrumentMgrPresenterImpl;
import com.regongzaixian.jiankong.instrument_mgr.uihelper.InstrumentAdapter;
import com.regongzaixian.jiankong.view_add_test_plan.view.AddTestPlanActivity;

import java.util.List;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:33
 * Description:
 */
public class InstrumentMgrActivity extends BaseActivity implements IInstrumentMgrView {

    protected static final int CREATE_OK = 1;
    public static final int MODIFY_OK = 2;
    public static final int DELETE_OK = 3;
    private IInstrumentMgrPresenter iInstrumentMgrPresenter;
    private TextView titleTextview;
    private ListView lvDevices;
    private InstrumentAdapter instrumentAdapter;
    private RelativeLayout footer;
    private TextView tvFooter;
    private Button btnAddToTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initPresenter();
        initData();
        getIntentFromOther();
    }

    private void getIntentFromOther() {
        Intent intent = getIntent();
        if (intent != null) {
            boolean fromAddTestDetail = intent.getBooleanExtra("fromAddTest", false);
            if (fromAddTestDetail) {
                btnAddToTest.setVisibility(View.VISIBLE);
            } else {
                btnAddToTest.setVisibility(View.GONE);
            }
        }
    }

    private void initView() {
        setContentView(R.layout.activity_test_mgr);
        initToolbar();

        btnAddToTest = (Button) findViewById(R.id.btn_add_to_test);
        btnAddToTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (instrumentAdapter.getSelectedData() == null) {
                    toast("请选择测试设备");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("instrumentEntity", instrumentAdapter.getSelectedData());
                setResult(AddTestPlanActivity.CHOOSE_INSTRUMENT_OK, intent);
                finish();
            }
        });


        lvDevices = (ListView) findViewById(R.id.lv_test_devices);
        instrumentAdapter = new InstrumentAdapter(this);
        lvDevices.setAdapter(instrumentAdapter);

        lvDevices.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int lastItemIndex;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && lastItemIndex == instrumentAdapter.getCount() - 1) {
                    Log.d("index--", "" + lastItemIndex);
                    //加载数据代码，此处省略了
                    if (lastItemIndex < InstrumentMgrPresenterImpl.PageLimitCount - 1) {
                        iInstrumentMgrPresenter.queryInstruments();
                    } else {
                        iInstrumentMgrPresenter.queryInstrumentsPage(lastItemIndex);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                //ListView 的FooterView也会算到visibleItemCount中去，所以要再减去一
                lastItemIndex = firstVisibleItem + visibleItemCount - 1 - 1;
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titleTextview = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleTextview.setText("测试设备数据库");
        titleTextview.setVisibility(View.VISIBLE);

        TextView addTv = (TextView) toolbar.findViewById(R.id.toolbar_right_menu);
        addTv.setText("新增");
        addTv.setVisibility(View.VISIBLE);
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstrumentMgrActivity.this, InstrumentDetailActivity.class);
                startActivityForResult(intent, CREATE_OK);
            }
        });

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CREATE_OK || resultCode == MODIFY_OK || resultCode == DELETE_OK) {
            iInstrumentMgrPresenter.queryInstruments();
        }
    }

    private void initData() {
        instrumentAdapter.setPresenter(iInstrumentMgrPresenter);
    }

    private void initPresenter() {
        iInstrumentMgrPresenter = new InstrumentMgrPresenterImpl();
        iInstrumentMgrPresenter.attachView(this);
        iInstrumentMgrPresenter.queryInstruments();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        showLoadingDialog();
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    @Override
    public void querySuccess(List<InstrumentEntity> devices) {
        instrumentAdapter.setDatas(devices);
        instrumentAdapter.notifyDataSetChanged();
        if (footer == null) {
            footer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.lv_footer, null);
            tvFooter = (TextView) footer.findViewById(R.id.tv_footer);
            lvDevices.addFooterView(footer);
        } else {
            tvFooter.setText("下拉加载更多...");
        }

        if (devices.size() <= 7) {
            tvFooter.setText("已无更多数据，请稍后重试");
        }
    }

    @Override
    public void queryPageSuccess(List<InstrumentEntity> devices) {
        if (devices.size() == 0) {
            tvFooter.setText("已无更多数据，请稍后重试");
            return;
        }
        instrumentAdapter.getDatas().addAll(devices);
        instrumentAdapter.notifyDataSetChanged();
    }

    @Override
    public void startModify(InstrumentEntity instrumentEntity) {
        Intent intent = new Intent(this, InstrumentDetailActivity.class);
        intent.putExtra("instrumentEntity", instrumentEntity);
        startActivityForResult(intent, 1);
    }
}
