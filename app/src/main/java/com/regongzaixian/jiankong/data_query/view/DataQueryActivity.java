package com.regongzaixian.jiankong.data_query.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.base.BaseActivity;
import com.regongzaixian.jiankong.data_query.frame.IDataQueryPresenter;
import com.regongzaixian.jiankong.data_query.frame.IDataQueryView;
import com.regongzaixian.jiankong.data_query.presenter.DataQueryPresenterImpl;
import com.regongzaixian.jiankong.data_query.uihelper.ExpandLvAdapter;
import com.regongzaixian.jiankong.model.SearchEntity;
import com.regongzaixian.jiankong.model.TestPlanGroup;

import java.util.List;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:38
 * Description:
 */
public class DataQueryActivity extends BaseActivity implements IDataQueryView {

    public static final int QUERY_SUCCESS = 110;
    private IDataQueryPresenter iDataQueryPresenter;
    private TextView titleTextview;
    private ExpandableListView expandLvTestPlan;
    private ExpandLvAdapter expandLvAdapter;
    private RelativeLayout rlEmpty;
    private SearchEntity searchEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initPresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == QUERY_SUCCESS) {
            List<TestPlanGroup> testPlanGroups = (List<TestPlanGroup>) data.getSerializableExtra("testPlanGroups");
            if (testPlanGroups != null && testPlanGroups.size() > 0) {
                expandLvAdapter.setDatas(testPlanGroups);
                for (int i = 0; i < testPlanGroups.size(); i++) {
                    expandLvTestPlan.expandGroup(i);
                }
                expandLvTestPlan.setVisibility(View.VISIBLE);
                rlEmpty.setVisibility(View.GONE);
            } else {
                expandLvTestPlan.setVisibility(View.GONE);
                rlEmpty.setVisibility(View.VISIBLE);
            }
            searchEntity = (SearchEntity) data.getSerializableExtra("searchEntity");
        }
    }

    private void initView() {
        setContentView(R.layout.activity_data_query);
        initToolbar();

        rlEmpty = (RelativeLayout) findViewById(R.id.rl_empty);
        expandLvTestPlan = (ExpandableListView) findViewById(R.id.lv_test_plan);
        expandLvAdapter = new ExpandLvAdapter(this);
        expandLvTestPlan.setAdapter(expandLvAdapter);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titleTextview = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleTextview.setText("数据查询");
        titleTextview.setVisibility(View.VISIBLE);


        ImageView search = (ImageView) toolbar.findViewById(R.id.toolbar_right_search);
        search.setVisibility(View.VISIBLE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataQueryActivity.this, SearchActivity.class);
                intent.putExtra("searchEntity", searchEntity);
                startActivityForResult(intent, 0);
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
        iDataQueryPresenter = new DataQueryPresenterImpl();
        iDataQueryPresenter.attachView(this);
//        iDataQueryPresenter.doSubmit();
        iDataQueryPresenter.queryLastDayTestPlan();
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
        if (testPlanEntities != null && testPlanEntities.size() > 0) {
            expandLvAdapter.setDatas(testPlanEntities);
            if (iDataQueryPresenter != null) {
                expandLvAdapter.setPresenter(iDataQueryPresenter);
            }

            for (int i = 0; i < testPlanEntities.size(); i++) {
                expandLvTestPlan.expandGroup(i);
            }

            rlEmpty.setVisibility(View.GONE);
            expandLvTestPlan.setVisibility(View.VISIBLE);
        } else {
            rlEmpty.setVisibility(View.VISIBLE);
            expandLvTestPlan.setVisibility(View.GONE);
        }
    }
}
