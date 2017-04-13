package com.regongzaixian.jiankong.data_query.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.base.BaseActivity;
import com.regongzaixian.jiankong.model.SearchEntity;
import com.regongzaixian.jiankong.model.TestPlanGroup;
import com.regongzaixian.jiankong.net.NetManager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
 * Time: 下午3:37
 * Description:
 */
public class SearchActivity extends BaseActivity {
    private TextView titleTextview;
    private EditText edDeviceContact;
    private EditText edDeviceName;
    private EditText edDeviceModel;
    private EditText edDeviceSerialNumber;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private EditText edMeanTemperature;
    private EditText edMeanHumidity;
    private Button btnSearch;
    private SearchEntity searchEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initIntent();
    }

    private void initIntent() {
        searchEntity = (SearchEntity) getIntent().getSerializableExtra("searchEntity");
        if (searchEntity != null) {
            if (searchEntity.getDevice_contact() != null) {
                edDeviceContact.setText(searchEntity.getDevice_contact());
            }
            if (searchEntity.getDevice_name() != null) {
                edDeviceName.setText(searchEntity.getDevice_name());
            }
            if (searchEntity.getDevice_serialnumber() != null) {
                edDeviceSerialNumber.setText(searchEntity.getDevice_serialnumber());
            }
            if (searchEntity.getCreatedAt() != null && searchEntity.getCreatedAt().getStartTime() != null) {
                tvStartTime.setText(searchEntity.getCreatedAt().getStartTime());
            }
            if (searchEntity.getCreatedAt() != null && searchEntity.getCreatedAt().getEndTime() != null) {
                tvEndTime.setText(searchEntity.getCreatedAt().getEndTime());
            }
            if (searchEntity.getMeantemperature() != null) {
                edMeanTemperature.setText(searchEntity.getMeantemperature());
            }
            if (searchEntity.getMeanhumidity() != null) {
                edMeanHumidity.setText(searchEntity.getMeanhumidity());
            }
        }
    }

    private void initData() {
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
                new DatePickerDialog(SearchActivity.this,
                        onDateSetListener,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        tvEndTime.setOnClickListener(new View.OnClickListener() {
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
                        tvEndTime.setText(sdf.format(dateAndTime.getTime()));
                    }
                };
                //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
                new DatePickerDialog(SearchActivity.this,
                        onDateSetListener,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalString = formatWhereQueryString();
                Log.d("json", finalString);
                queryForWhere(finalString);
            }
        });
    }

    private void queryForWhere(String finalString) {
        showLoadingDialog();
        Map<String, String> params = new HashMap<>();
        params.put("where", finalString);
        params.put("populate", "device,instrument");
        params.put("sort", "createdAt DESC");
        Observable<List<TestPlanGroup>> observable = NetManager.getInstance().getApiService().searchByWhere(params);

        NetManager.getInstance().runRxJava(observable, new Subscriber<List<TestPlanGroup>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                Toast.makeText(SearchActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<TestPlanGroup> testPlanGroups) {
                hideLoadingDialog();
                Intent intent = new Intent();
                intent.putExtra("testPlanGroups", (Serializable) testPlanGroups);
                intent.putExtra("searchEntity", searchEntity);
                setResult(DataQueryActivity.QUERY_SUCCESS, intent);
                finish();
            }
        });
    }

    private String formatWhereQueryString() {
        String device_contact = edDeviceContact.getText().toString();
        String device_model = edDeviceModel.getText().toString();
        String deviceName = edDeviceName.getText().toString();
        String device_serialnumber = edDeviceSerialNumber.getText().toString();
        String startTime = tvStartTime.getText().toString();
        String endTime = tvEndTime.getText().toString();
        String meantemperature = edMeanTemperature.getText().toString();
        String meanhumidity = edMeanHumidity.getText().toString();
        searchEntity = new SearchEntity();
        if (!device_contact.equals("")) {
            searchEntity.setDevice_contact(device_contact);
        }
        if (!deviceName.equals("")) {
            searchEntity.setDevice_name(deviceName);
        }
        if (!device_model.equals("")) {
            searchEntity.setDevice_model(device_model);
        }
        if (!device_serialnumber.equals("")) {
            searchEntity.setDevice_serialnumber(device_serialnumber);
        }
        if (!startTime.equals("请选择") || !endTime.equals("请选择")) {
            SearchEntity.CreatedAtBean createdAtBean = new SearchEntity.CreatedAtBean();
            searchEntity.setCreatedAt(createdAtBean);
            if (!startTime.equals("请选择")) {
                searchEntity.getCreatedAt().setStartTime(startTime);
            }
            if (!endTime.equals("请选择")) {
                searchEntity.getCreatedAt().setEndTime(endTime);
            }
        }
        if (!meantemperature.equals("")) {
            searchEntity.setMeantemperature(meantemperature);
        }
        if (!meanhumidity.equals("")) {
            searchEntity.setMeanhumidity(meanhumidity);
        }
        String json = new Gson().toJson(searchEntity);
        //将startTime，endTime replace成'>' '<'
        return json.replace("startTime", ">").replace("endTime", "<");
    }

    private void initView() {
        setContentView(R.layout.activity_search);
        initToolbar();

        edDeviceContact = (EditText) findViewById(R.id.ed_device_contact);
        edDeviceName = (EditText) findViewById(R.id.ed_device_name);
        edDeviceModel = (EditText) findViewById(R.id.ed_device_model);
        edDeviceSerialNumber = (EditText) findViewById(R.id.ed_device_serial_number);
        tvStartTime = (TextView) findViewById(R.id.tv_starttime);
        tvEndTime = (TextView) findViewById(R.id.tv_endtime);
        edMeanTemperature = (EditText) findViewById(R.id.ed_mean_temperature);
        edMeanHumidity = (EditText) findViewById(R.id.ed_mean_humidity);
        btnSearch = (Button) findViewById(R.id.btn_search);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titleTextview = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleTextview.setText("输入搜索条件");
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
