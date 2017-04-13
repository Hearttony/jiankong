package com.regongzaixian.jiankong.view_add_test_plan.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.base.BaseActivity;
import com.regongzaixian.jiankong.model.TestDataEntity;
import com.regongzaixian.jiankong.model.TestPlanDetailEntity;
import com.regongzaixian.jiankong.util.DialogUtil;
import com.regongzaixian.jiankong.util.Preferences;
import com.regongzaixian.jiankong.util.materialdialog.IPromptDilaog;
import com.regongzaixian.jiankong.util.materialdialog.PromptDialog;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanDetailPresenter;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanDetailView;
import com.regongzaixian.jiankong.view_add_test_plan.presenter.ViewTestPlanDetailPresenterImpl;
import com.regongzaixian.jiankong.view_add_test_plan.uiHelper.DataTableAdapter;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/5
 * Time: 下午8:31
 * Description:
 */
public class ViewTestPlanDetailActivity extends BaseActivity implements IViewTestPlanDetailView {

    private TextView titleTextview;
    private int testPlanId;
    private TextView tvInstrument;
    private TextView tvDevice;
    private TextView tvStatus;
    private TextView tvSampleQuantity;
    private TextView tvCycle;
    private TextView tvTempSensorNum;
    private TextView tvStartTime;
    private TextView tvTemp;
    private TextView tvHum;
    private EditText edEmailAddress;
    private Button btnSendEmail;
    private Button btnPause;
    private Button btnDelete;
    private IViewTestPlanDetailPresenter iViewTestPlanDetailPresenter;
    private TableFixHeaders tableFixHeaders;
    private ScrollView scrollView;
    private DataTableAdapter tableAdapter;
    private TestPlanDetailEntity testPlanDetailEntity;
    private TextView tvNoData;
    private boolean fromDataQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testPlanId = getIntent().getIntExtra("testPlanId", 0);
        fromDataQuery = getIntent().getBooleanExtra("fromDataQuery", false);
        initView();
        initData();
        initPresenter();
    }

    private void initData() {
        tableFixHeaders.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrollView.requestDisallowInterceptTouchEvent(false);//拦截list的点击事件
                } else {
                    scrollView.requestDisallowInterceptTouchEvent(true);//不拦截list的点击事件
                }
                return false;
            }
        });
        tableAdapter = new DataTableAdapter(this, testPlanId);
        tableFixHeaders.setAdapter(tableAdapter);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTo;
                String emailString = Preferences.getInstance().getEmail();
                if (!edEmailAddress.getText().toString().isEmpty()) {
                    emailTo = edEmailAddress.getText().toString();
                } else {
                    emailTo = emailString;
                }
                iViewTestPlanDetailPresenter.sendEmail(testPlanId, emailTo);
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iViewTestPlanDetailPresenter.stopTestPlan(testPlanId, testPlanDetailEntity);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromptDialog.Builder dialog = DialogUtil.createDialog(getContext(),
                        "确定删除此次测试计划吗?", "确定", "取消",
                        new IPromptDilaog() {

                            @Override
                            public void onOK() {
                                iViewTestPlanDetailPresenter.deleteTestPlan(testPlanId);

                            }

                            @Override
                            public void onCancle() {
                            }
                        }, true);
                dialog.show();

            }
        });
        edEmailAddress.setText(Preferences.getInstance().getBakEmail());

        checkFromDataQuery();
    }

    private void checkFromDataQuery() {
        if (fromDataQuery) {
            btnPause.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }
    }

    private void initPresenter() {
        iViewTestPlanDetailPresenter = new ViewTestPlanDetailPresenterImpl();
        iViewTestPlanDetailPresenter.attachView(this);
        iViewTestPlanDetailPresenter.queryTestPlanDetail(testPlanId);

        iViewTestPlanDetailPresenter.queryTestPlanDetailData(testPlanId);

        //set presenter to adapter
        tableAdapter.setPresenter(iViewTestPlanDetailPresenter);
    }

    private void initView() {
        setContentView(R.layout.activity_test_plan_detail);
        initToolbar();

        tvInstrument = (TextView) findViewById(R.id.tv_instrument);
        tvDevice = (TextView) findViewById(R.id.tv_device);
        tvStatus = (TextView) findViewById(R.id.tv_status);
        tvSampleQuantity = (TextView) findViewById(R.id.tv_num);
        tvCycle = (TextView) findViewById(R.id.tv_cycle);
        tvTempSensorNum = (TextView) findViewById(R.id.tv_temp_sensor_num);
        tvStartTime = (TextView) findViewById(R.id.tv_start_time);
        tvTemp = (TextView) findViewById(R.id.tv_temp);
        tvHum = (TextView) findViewById(R.id.tv_hum);
        edEmailAddress = (EditText) findViewById(R.id.ed_email_address);

        btnSendEmail = (Button) findViewById(R.id.btn_send_email);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnDelete = (Button) findViewById(R.id.btn_delete_test_plan);


        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
        tvNoData = (TextView) findViewById(R.id.tv_no_data);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titleTextview = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleTextview.setText("采集信息详情");
        titleTextview.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void queryDetailSuccess(TestPlanDetailEntity testPlanDetailEntity) {
        this.testPlanDetailEntity = testPlanDetailEntity;
        tvInstrument.setText("" + testPlanDetailEntity.getInstrument().getName());
        tvDevice.setText("" + testPlanDetailEntity.getDevice().getName());
        tvSampleQuantity.setText("" + testPlanDetailEntity.getSamplequantity());
        tvCycle.setText("" + testPlanDetailEntity.getSampleinterval());
        tvTempSensorNum.setText("" + testPlanDetailEntity.getTemperaturesensorcount());
        tvStartTime.setText("" + testPlanDetailEntity.getStarttime());
        tvTemp.setText("" + testPlanDetailEntity.getTemperatureExt());
        tvHum.setText("" + testPlanDetailEntity.getHumidityExt());

        String status = testPlanDetailEntity.getTeststatus();
        if (status.equals("notstart")) {
            status = "未开始";
            btnPause.setVisibility(View.GONE);
            btnDelete.setVisibility(View.VISIBLE);
        } else if (status.equals("running")) {
            status = "运行中";
            btnPause.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.GONE);
        } else if (status.equals("stopped")) {
            status = "停止";
            btnPause.setVisibility(View.GONE);
            btnDelete.setVisibility(View.VISIBLE);
        }
        tvStatus.setText("" + status);
        checkFromDataQuery();
    }

    @Override
    public void deleteTestPlanSuccess() {
        setResult(ViewTestPlanActivity.DELETE_OK);
        finish();
    }

    @Override
    public void stopSuccess(TestPlanDetailEntity testPlanDetailEntity1) {
        this.testPlanDetailEntity = testPlanDetailEntity1;
        String status = testPlanDetailEntity1.getTeststatus();
        if (status.equals("notstart")) {
            status = "未开始";
        } else if (status.equals("stopped")) {
            status = "停止";
        }
        tvStatus.setText("" + status);
        btnPause.setVisibility(View.GONE);
        btnDelete.setVisibility(View.VISIBLE);
        sendBroadcast(new Intent("refresh_test_plan_list"));
        checkFromDataQuery();
    }

    @Override
    public void queryDetailsDatas(TestDataEntity testDataEntity) {
        if (testDataEntity.getHeaders() != null && testDataEntity.getTestdata() != null) {
            tableAdapter.setDatas(testDataEntity);
            tableFixHeaders.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
        }

        checkFromDataQuery();

    }

    @Override
    public void queryDetailsDatasPage(TestDataEntity testDataEntity) {
        //如果查不出更多数据了，禁止再次请求
        if (testDataEntity.getTestdata() == null) {
            toast("已无更多数据，请稍后再查看");
        } else {
            tableAdapter.addPageData(testDataEntity.getTestdata());
        }
        checkFromDataQuery();
    }
}
