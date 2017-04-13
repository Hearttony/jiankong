package com.regongzaixian.jiankong.view_add_test_plan.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.base.BaseActivity;
import com.regongzaixian.jiankong.model.TestPlanGroup;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanPresenter;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanView;
import com.regongzaixian.jiankong.view_add_test_plan.presenter.ViewTestPlanPresenterImpl;
import com.regongzaixian.jiankong.view_add_test_plan.uiHelper.ExpandLvAdapter;

import java.util.List;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:32
 * Description:
 */
public class ViewTestPlanActivity extends BaseActivity implements IViewTestPlanView {

    public static final int CREATE_OK = 1;
    public static final int DELETE_OK = 2;
    private IViewTestPlanPresenter iViewTestPlanPresenter;
    private TextView titleTextview;
    private ExpandableListView expandLvTestPlan;
    private ExpandLvAdapter expandLvAdapter;
    private BroadcastReceiver refreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            iViewTestPlanPresenter.queryTestPlan();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initPresenter();
        registerBroadcast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (refreshReceiver != null) {
            unregisterReceiver(refreshReceiver);
        }
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("refresh_test_plan_list");
        registerReceiver(refreshReceiver, filter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CREATE_OK) {
            iViewTestPlanPresenter.queryTestPlan();
        }

        if (resultCode == DELETE_OK) {
            iViewTestPlanPresenter.queryTestPlan();
        }
    }

    private void initView() {
        setContentView(R.layout.activity_view_add_test);
        initToolbar();

        expandLvTestPlan = (ExpandableListView) findViewById(R.id.lv_test_plan);
        expandLvAdapter = new ExpandLvAdapter(this);
        expandLvTestPlan.setAdapter(expandLvAdapter);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titleTextview = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleTextview.setText("正在进行的测试");
        titleTextview.setVisibility(View.VISIBLE);

        TextView addTv = (TextView) toolbar.findViewById(R.id.toolbar_right_menu);
        addTv.setText("新增");
        addTv.setVisibility(View.VISIBLE);
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewTestPlanActivity.this, AddTestPlanActivity.class);
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

    private void initData() {
        expandLvTestPlan.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition,
                                        long id) {
                return true;
            }
        });
    }

    private void initPresenter() {
        iViewTestPlanPresenter = new ViewTestPlanPresenterImpl();
        iViewTestPlanPresenter.attachView(this);
        iViewTestPlanPresenter.queryTestPlan();
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
    public void querySuccess(List<TestPlanGroup> testPlanEntities) {
        expandLvAdapter.setDatas(testPlanEntities);
        if (iViewTestPlanPresenter != null) {
            expandLvAdapter.setPresenter(iViewTestPlanPresenter);
        }

        for (int i = 0; i < testPlanEntities.size(); i++) {
            expandLvTestPlan.expandGroup(i);
        }
    }
}
