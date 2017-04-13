package com.regongzaixian.jiankong.view_add_test_plan.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.base.BaseActivity;
import com.regongzaixian.jiankong.model.DeviceEntity;
import com.regongzaixian.jiankong.model.InstrumentEntity;
import com.regongzaixian.jiankong.model.StandardEntity;
import com.regongzaixian.jiankong.model.TestPlanGroup;
import com.regongzaixian.jiankong.net.NetManager;
import com.regongzaixian.jiankong.instrument_mgr.view.InstrumentMgrActivity;
import com.regongzaixian.jiankong.device_mgr.view.DeviceMgrActivity;
import com.regongzaixian.jiankong.util.DialogUtil;
import com.regongzaixian.jiankong.util.materialdialog.IPromptDilaog;
import com.regongzaixian.jiankong.util.materialdialog.PromptDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:32
 * Description:
 */
public class AddTestPlanActivity extends BaseActivity {
    private static final int CHOOSE_DEVICE = 1;
    private static final int CHOOSE_INSTRUMENT = 2;
    public static final int CHOOSE_DEVICE_OK = 1;
    public static final int CHOOSE_INSTRUMENT_OK = 2;
    private TextView titleTextview;
    private RelativeLayout rlTested;
    private RelativeLayout rlTest;
    private RelativeLayout rlStandard;
    private EditText edTemp;
    private EditText edHum;
    private EditText edCycle;
    private EditText edNum;
    private TextView tvStartTime;
    private EditText edTempSensorNum;
    private EditText edHumSensorNum;
    private EditText edTestTemp;
    private EditText edTestHum;
    private TextView tvTested;
    private TextView tvTest;
    private TextView tvStandard;
    private Button btnTestPlan;
    private DeviceEntity deviceEntity;
    private InstrumentEntity instrumentEntity;
    private StandardEntity standardEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CHOOSE_DEVICE_OK) {
            deviceEntity = (DeviceEntity) data.getSerializableExtra("deviceEntity");
            tvTested.setText(deviceEntity.getName());
        }

        if (resultCode == CHOOSE_INSTRUMENT_OK) {
            instrumentEntity = (InstrumentEntity) data.getSerializableExtra("instrumentEntity");
            tvTest.setText(instrumentEntity.getName());
        }
    }

    private void initView() {
        setContentView(R.layout.activity_add_test_detail);
        initToolbar();

        rlTested = (RelativeLayout) findViewById(R.id.rl_tested);
        rlTest = (RelativeLayout) findViewById(R.id.rl_test);
        rlStandard = (RelativeLayout) findViewById(R.id.rl_standard);
        tvTested = (TextView) rlTested.findViewById(R.id.tv_tested);
        tvTest = (TextView) rlTest.findViewById(R.id.tv_test);
        tvStandard = (TextView) rlStandard.findViewById(R.id.tv_standard);

        edTemp = (EditText) findViewById(R.id.ed_temp);
        edHum = (EditText) findViewById(R.id.ed_hum);
        edCycle = (EditText) findViewById(R.id.ed_cycle);
        edNum = (EditText) findViewById(R.id.ed_num);
        tvStartTime = (TextView) findViewById(R.id.tv_starttime);

        edTempSensorNum = (EditText) findViewById(R.id.ed_temp_sensor_number);
        edHumSensorNum = (EditText) findViewById(R.id.ed_hum_sensor_number);
        edTestTemp = (EditText) findViewById(R.id.ed_test_temp);
        edTestHum = (EditText) findViewById(R.id.ed_test_hum);
        btnTestPlan = (Button) findViewById(R.id.btn_test_plan);

        rlTested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTestPlanActivity.this, DeviceMgrActivity.class);
                intent.putExtra("fromAddTest", true);
                startActivityForResult(intent, CHOOSE_DEVICE);
            }
        });
        rlTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTestPlanActivity.this, InstrumentMgrActivity.class);
                intent.putExtra("fromAddTest", true);
                startActivityForResult(intent, CHOOSE_INSTRUMENT);
            }
        });
        rlStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStandardList();
            }
        });
        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取一个日历对象
                final Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
                //当点击DatePickerDialog控件的设置按钮时，调用该方法
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //修改日历控件的年，月，日
                        //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
                        dateAndTime.set(Calendar.YEAR, year);
                        dateAndTime.set(Calendar.MONTH, monthOfYear);
                        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        //将页面TextView的显示更新为最新时间
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        tvStartTime.setText(sdf.format(dateAndTime.getTime()));
                    }
                };
                //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
                new DatePickerDialog(AddTestPlanActivity.this,

                        onDateSetListener,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnTestPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkMsgAllow()) {
                    return;
                }
                postTestPlan();
            }
        });

    }

    private boolean checkMsgAllow() {
        if (deviceEntity == null) {
            toast("请选择被测设备");
            return false;
        }
        if (instrumentEntity == null) {
            toast("请选择测试设备");
            return false;
        }
        if (standardEntity == null) {
            toast("请选择技术文件");
            return false;
        }
        if (edTemp.getText().toString().isEmpty()) {
            toast("请填写环境温度");
            return false;
        }
        if (edHum.getText().toString().isEmpty()) {
            toast("请填写环境湿度");
            return false;
        }
        if (edCycle.getText().toString().isEmpty()) {
            toast("请填写采样周期");
            return false;
        }
        if (edNum.getText().toString().isEmpty()) {
            toast("请填写采样数量");
            return false;
        }
        if (edTempSensorNum.getText().toString().isEmpty()) {
            toast("请填写温度传感器数");
            return false;
        }
        if (edHumSensorNum.getText().toString().isEmpty()) {
            toast("请填写湿度传感器数");
            return false;
        }
        if (edTestTemp.getText().toString().isEmpty()) {
            toast("请填写测试温度");
            return false;
        }
        if (edTestHum.getText().toString().isEmpty()) {
            toast("请填写测试湿度");
            return false;
        }

        if (tvStartTime.getText().toString().equals("请选择")) {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tvStartTime.setText(sdf.format(calendar.getTime()));
            return true;
        }

        return true;
    }

    /**
     * 创建测试计划（开始采集）
     */
    private void postTestPlan() {
        showLoadingDialog();
        Map<String, String> params = new HashMap<>();
        params.put("sampleinterval", "" + edCycle.getText().toString());
        params.put("samplequantity", "" + edNum.getText().toString());
        params.put("starttime", "" + tvStartTime.getText().toString());
        params.put("temperaturesensorcount", "" + edTempSensorNum.getText().toString());
        params.put("humiditysensorcount", "" + edHumSensorNum.getText().toString());
        params.put("temperatureExt", "" + edTemp.getText().toString());
        params.put("humidityExt", "" + edHum.getText().toString());
        params.put("meantemperature", "" + edTestTemp.getText().toString());
        params.put("meanhumidity", "" + edTestHum.getText().toString());
        params.put("instrument", "" + instrumentEntity.getId());
        params.put("device", "" + deviceEntity.getId());
        params.put("queryStandard", "" + standardEntity.getId());
        Observable<TestPlanGroup> observable = NetManager.getInstance().getApiService().createTestPlan(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<TestPlanGroup>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                toast(e.getMessage());
            }

            @Override
            public void onNext(TestPlanGroup testPlanGroup) {
                hideLoadingDialog();
                toast("创建成功");
                setResult(ViewTestPlanActivity.CREATE_OK);
                finish();
            }
        });
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 获取技术文件
     */
    private void getStandardList() {
        showLoadingDialog();
        Observable<List<StandardEntity>> observable = NetManager.getInstance().getApiService().queryStandard();
        NetManager.getInstance().runRxJava(observable, new Subscriber<List<StandardEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                Toast.makeText(AddTestPlanActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<StandardEntity> standardEntities) {
                hideLoadingDialog();
                showSelectDialog(standardEntities);
            }
        });
    }

    private void showSelectDialog(final List<StandardEntity> standardEntities) {
        final List<String> chooseList = new ArrayList<>();
        for (StandardEntity standardEntity : standardEntities) {
            chooseList.add(standardEntity.getName());
        }
        final PromptDialog.Builder dialog = DialogUtil.createDialog(this, "选择依据技术文件",
                "", "确定", "取消",
                new IPromptDilaog() {

                    @Override
                    public void onOK() {
                        if (standardEntity != null) {
                            tvStandard.setText(standardEntity.getName());
                        } else {
                            toast("请选择依据技术文件");
                        }
                    }

                    @Override
                    public void onCancle() {
                    }
                }, true);
        dialog.setOnListItemClickListener(new PromptDialog.OnListDialogItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                standardEntity = standardEntities.get(position);
                if (dialog.getListViewAdapter() != null) {
                    dialog.getListViewAdapter().setSelectPosition(position);
                    dialog.getListViewAdapter().refreshAdapter();
                }
            }
        });
        dialog.setDialogType(PromptDialog.DialogType.LIST_DIALOG);
        dialog.setListItemData(chooseList);
        dialog.show();

        if (dialog.getListViewAdapter() != null) {
            standardEntity = standardEntities.get(0);
            dialog.getListViewAdapter().setSelectPosition(0);
            dialog.getListViewAdapter().refreshAdapter();
        }
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titleTextview = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleTextview.setText("设置采集信息");
        titleTextview.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
