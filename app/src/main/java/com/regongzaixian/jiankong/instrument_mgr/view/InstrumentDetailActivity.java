package com.regongzaixian.jiankong.instrument_mgr.view;

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
import com.regongzaixian.jiankong.model.InstrumentEntity;
import com.regongzaixian.jiankong.net.NetManager;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:33
 * Description:
 */
public class InstrumentDetailActivity extends BaseActivity {
    private TextView titleTextview;
    private EditText edName;
    private EditText edModel;
    private EditText edCertNumber;
    private EditText edSerialNumber;
    private EditText edTechInfo;
    private EditText edValidDate;
    private EditText edManufacture;
    private EditText edRemark;
    private Button btnSave;
    private Button btnModify;
    private Button btnDelete;
    private InstrumentEntity instrumentEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_test_detail);
        initToolbar();

        edName = (EditText) findViewById(R.id.ed_name);
        edModel = (EditText) findViewById(R.id.ed_device_model);
        edCertNumber = (EditText) findViewById(R.id.ed_certnumber);
        edSerialNumber = (EditText) findViewById(R.id.ed_device_serial_number);
        edTechInfo = (EditText) findViewById(R.id.ed_techinfo);
        edValidDate = (EditText) findViewById(R.id.ed_validdate);
        edManufacture = (EditText) findViewById(R.id.ed_manufacture);
        edRemark = (EditText) findViewById(R.id.ed_remark);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageCheck()) {
                    return;
                }
                createInstrument();
            }
        });

        btnModify = (Button) findViewById(R.id.btn_modify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageCheck()) {
                    return;
                }
                modifyInstrument();
            }
        });
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageCheck()) {
                    return;
                }
                deleteInstrument();
            }
        });
    }

    private boolean messageCheck() {
        if (edName.getText().toString().isEmpty() ||
                edModel.getText().toString().isEmpty() ||
                edCertNumber.getText().toString().isEmpty() ||
                edSerialNumber.getText().toString().isEmpty() ||
                edTechInfo.getText().toString().isEmpty() ||
                edValidDate.getText().toString().isEmpty() ||
                edManufacture.getText().toString().isEmpty() ||
                edRemark.getText().toString().isEmpty()) {
            Toast.makeText(InstrumentDetailActivity.this, "请检查所填信息", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void initData() {
        instrumentEntity = (InstrumentEntity) getIntent().getSerializableExtra("instrumentEntity");
        if (instrumentEntity != null) {
            edName.setText(instrumentEntity.getName());
            edModel.setText(instrumentEntity.getModel());
            edCertNumber.setText(instrumentEntity.getCertnumber());
            edSerialNumber.setText(instrumentEntity.getSerialnumber());
            edTechInfo.setText(instrumentEntity.getTechInfo());
            edValidDate.setText(instrumentEntity.getValiddate());
            edManufacture.setText(instrumentEntity.getManufacture());
            edRemark.setText(instrumentEntity.getRemark());

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
    private void createInstrument() {
        showLoadingDialog();
        Map<String, String> params = new HashMap<>();
        params.put("certnumber", edCertNumber.getText().toString());
        params.put("name", edName.getText().toString());
        params.put("serialnumber", edSerialNumber.getText().toString());
        params.put("model", edModel.getText().toString());
        params.put("techInfo", edTechInfo.getText().toString());
        params.put("validdate", edValidDate.getText().toString());
        params.put("remark", edRemark.getText().toString());
        params.put("manufacture", edManufacture.getText().toString());
        Observable<InstrumentEntity> observable = NetManager.getInstance().getApiService().createInstrument(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<InstrumentEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                Toast.makeText(InstrumentDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(InstrumentEntity instrumentEntity) {
                hideLoadingDialog();
                Toast.makeText(InstrumentDetailActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                setResult(InstrumentMgrActivity.CREATE_OK);
                finish();
            }
        });
    }


    /**
     * 修改测试设备信息
     */
    private void modifyInstrument() {
        showLoadingDialog();
        Map<String, String> params = new HashMap<>();
        params.put("certnumber", edCertNumber.getText().toString());
        params.put("name", edName.getText().toString());
        params.put("serialnumber", edSerialNumber.getText().toString());
        params.put("model", edModel.getText().toString());
        params.put("techInfo", edTechInfo.getText().toString());
        params.put("validdate", edValidDate.getText().toString());
        params.put("remark", edRemark.getText().toString());
        params.put("manufacture", edManufacture.getText().toString());
        Observable<InstrumentEntity> observable = NetManager.getInstance().getApiService().modifyInstrument(instrumentEntity.getId(), params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<InstrumentEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                Toast.makeText(InstrumentDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(InstrumentEntity instrumentEntity) {
                hideLoadingDialog();
                Toast.makeText(InstrumentDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                setResult(InstrumentMgrActivity.MODIFY_OK);
                finish();
            }
        });
    }

    /**
     * 删除测试设备信息
     */
    private void deleteInstrument() {
        showLoadingDialog();
        Observable<InstrumentEntity> observable = NetManager.getInstance().getApiService().deleteInstrument(instrumentEntity.getId());
        NetManager.getInstance().runRxJava(observable, new Subscriber<InstrumentEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                hideLoadingDialog();
                Toast.makeText(InstrumentDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(InstrumentEntity instrumentEntity) {
                hideLoadingDialog();
                Toast.makeText(InstrumentDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                setResult(InstrumentMgrActivity.DELETE_OK);
                finish();
            }
        });
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        titleTextview = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleTextview.setText("测试设备信息");
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
