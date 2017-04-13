package com.regongzaixian.jiankong.device_mgr.presenter;

import com.regongzaixian.jiankong.model.DeviceEntity;
import com.regongzaixian.jiankong.net.NetManager;
import com.regongzaixian.jiankong.device_mgr.frame.IDeviceMgrPresenter;
import com.regongzaixian.jiankong.device_mgr.frame.IDeviceMgrView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:33
 * Description:
 */
public class DeviceMgrPresenterImpl extends IDeviceMgrPresenter {
    public static final int PageLimitCount = 8;

    @Override
    public void attachView(IDeviceMgrView iView) {
        super.attachView(iView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void queryDevices() {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("sort", "id desc");
        params.put("limit", "" + PageLimitCount);//返回前8条数据
        Observable<List<DeviceEntity>> observable = NetManager.getInstance().getApiService().queryDevices(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<List<DeviceEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(List<DeviceEntity> devices) {
                getiView().hideProgress();
                getiView().querySuccess(devices);
            }
        });
    }

    @Override
    public void queryDevicePage(int currentLastItemIndex) {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("sort", "id desc");
        params.put("skip", "" + (currentLastItemIndex + 1));
        params.put("limit", "" + PageLimitCount);
        Observable<List<DeviceEntity>> observable = NetManager.getInstance().getApiService().queryDeviceForPage(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<List<DeviceEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(List<DeviceEntity> devices) {
                getiView().hideProgress();
                getiView().queryPageSuccess(devices);
            }
        });
    }

    @Override
    public void gotoModifyDetail(DeviceEntity deviceEntity) {
        getiView().startModify(deviceEntity);
    }
}
