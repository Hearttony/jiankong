package com.regongzaixian.jiankong.device_mgr.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshikeji.xjapp.R;
import com.regongzaixian.jiankong.base.BaseActivity;
import com.regongzaixian.jiankong.model.DeviceEntity;
import com.regongzaixian.jiankong.net.NetManager;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:32
 * Description:
 */
public class DeviceDetailActivity extends BaseActivity {
    private TextView titleTextview;
    private EditText edContact;
    private EditText edAddress;
    private EditText edDevice;
    private EditText edModel;
    private EditText edSerialNum;
    private EditText edManufacture;
    private EditText edRemark;
    private Button btnSave;
    private Button btnModify;
    private Button btnDelete;
    private DeviceEntity deviceEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_tested_detail);
        initToolbar();

        edContact = (EditText) findViewById(R.id.ed_contact);
        edAddress = (EditText) findViewById(R.id.ed_address);
        edDevice = (EditText) findViewById(R.id.ed_device);
        edModel = (EditText) findViewById(R.id.ed_device_model);
        edSerialNum = (EditText) findViewById(R.id.ed_serial_number);
        edManufacture = (EditText) findViewById(R.id.ed_manufacture);
        edRemark = (EditText) findViewById(R.id.ed_remark);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageCheck()) {
                    return;
                }
                createDevice();
            }
        });

        btnModify = (Button) findViewById(R.id.btn_modify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageCheck()) {
                    return;
                }
                modifyDevice();
            }
        });
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageCheck()) {
                    return;
                }
                deleteDevice();
            }
        });
    }

    private boolean messageCheck() {
        if (edContact.getText().toString().isEmpty() ||
                edAddress.getText().toString().isEmpty() ||
                edDevice.getText().toString().isEmpty() ||
                edModel.getText().toString().isEmpty() ||
                edSerialNum.getText().toString().isEmpty() ||
                edManufacture.getText().toString().isEmpty() ||
                edRemark.getText().toString().isEmpty()) {
            Toast.makeText(DeviceDetailActivity.this, "请检查所填信息", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void initData() {
        deviceEntity = (DeviceEntity) getIntent().getSerializableExtra("deviceEntity");
        if (deviceEntity != null) {
            edContact.setText(deviceEntity.getContact());
            edAddress.setText(deviceEntity.getAddress());
            edDevice.setText(deviceEntity.getName());
            edModel.setText(deviceEntity.getModel());
            edSerialNum.setText(deviceEntity.getSerialnumber());
            edManufacture.setText(deviceEntity.getManufacture());
            edRemark.setText(deviceEntity.getRemark());

            btnSave.setVisibility(View.GONE);
            btnModify.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnSave.setVisibility(View.VISIBLE);
            btnModify.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }
    }

    /**
     * 创建测试设备
     */
    private void createDevice() {
        showLoadingDialog();
        Map<String, String> params = new HashMap<>();
        params.put("name", edDevice.getText().toString());
        params.put("contact", edContact.getText().toString());
        params.put("address", edAddress.getText().toString());
        params.put("model", edModel.getText().toString());
        params.put("serialnumber", edSerialNum.getText().toString());
        params.put("manufacture", edManufacture.getText().toString());
        params.put("remark", edRemark.getText().toString());
        Observable<DeviceEntity> observable = NetManager.getInstance().getApiService().createDevice(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<DeviceEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                Toast.makeText(DeviceDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(DeviceEntity deviceEntity) {
                hideLoadingDialog();
                Toast.makeText(DeviceDetailActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                setResult(DeviceMgrActivity.CREATE_OK);
                finish();
            }
        });
    }


    /**
     * 修改测试设备信息
     */
    private void modifyDevice() {
        showLoadingDialog();
        Map<String, String> params = new HashMap<>();
        params.put("name", edDevice.getText().toString());
        params.put("contact", edContact.getText().toString());
        params.put("address", edAddress.getText().toString());
        params.put("model", edModel.getText().toString());
        params.put("serialnumber", edSerialNum.getText().toString());
        params.put("manufacture", edManufacture.getText().toString());
        params.put("remark", edRemark.getText().toString());
        Observable<DeviceEntity> observable = NetManager.getInstance().getApiService().modifyDevice(deviceEntity.getId(), params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<DeviceEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                Toast.makeText(DeviceDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(DeviceEntity deviceEntity) {
                hideLoadingDialog();
                Toast.makeText(DeviceDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                setResult(DeviceMgrActivity.MODIFY_OK);
                finish();
            }
        });
    }

    /**
     * 删除测试设备信息
     */
    private void deleteDevice() {
        showLoadingDialog();
        Observable<DeviceEntity> observable = NetManager.getInstance().getApiService().deleteDevice(deviceEntity.getId());
        NetManager.getInstance().runRxJava(observable, new Subscriber<DeviceEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                Toast.makeText(DeviceDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(DeviceEntity deviceEntity) {
                hideLoadingDialog();
                Toast.makeText(DeviceDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                setResult(DeviceMgrActivity.DELETE_OK);
                finish();
            }
        });
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titleTextview = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleTextview.setText("被测试设备信息");
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
