package com.regongzaixian.jiankong.view_add_test_plan.presenter;

import com.regongzaixian.jiankong.model.TestDataEntity;
import com.regongzaixian.jiankong.model.TestPlanDetailEntity;
import com.regongzaixian.jiankong.net.NetManager;
import com.regongzaixian.jiankong.util.Preferences;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanDetailPresenter;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanDetailView;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:31
 * Description:
 */
public class ViewTestPlanDetailPresenterImpl extends IViewTestPlanDetailPresenter {

    @Override
    public void attachView(IViewTestPlanDetailView iView) {
        super.attachView(iView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void queryTestPlanDetail(int testPlanId) {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("populate", "instrument,device");//展开测试，被测对象，并返回
        Observable<TestPlanDetailEntity> observable = NetManager.getInstance().getApiService().queryTestPlanDetail(testPlanId, params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<TestPlanDetailEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(TestPlanDetailEntity testPlanDetailEntity) {
                getiView().hideProgress();
                getiView().queryDetailSuccess(testPlanDetailEntity);
            }
        });
    }

    @Override
    public void deleteTestPlan(int testPlanId) {
        getiView().showProgress();
        Observable<Object> observable = NetManager.getInstance().getApiService().deleteTestPlan(testPlanId);

        NetManager.getInstance().runRxJava(observable, new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                getiView().hideProgress();
                getiView().toast("删除成功");
                getiView().deleteTestPlanSuccess();
            }
        });
    }

    @Override
    public void sendEmail(int testPlanId, final String emailTo) {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("planid", "" + testPlanId);
        params.put("to", "" + emailTo);
        Observable<Object> observable = NetManager.getInstance().getApiService().sendEmail(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                getiView().hideProgress();
                getiView().toast("已发送");
                if (emailTo != Preferences.getInstance().getEmail()) {
                    Preferences.getInstance().storeBakEmail(emailTo);
                }
            }
        });
    }

    @Override
    public void stopTestPlan(int testPlanId, TestPlanDetailEntity testPlanDetailEntity) {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("sampleinterval", testPlanDetailEntity.getSamplequantity());
        params.put("samplequantity", testPlanDetailEntity.getSamplequantity());
        params.put("temperaturesensorcount", testPlanDetailEntity.getTemperaturesensorcount());
        params.put("humiditysensorcount", testPlanDetailEntity.getHumiditysensorcount());
        params.put("temperatureExt", testPlanDetailEntity.getTemperatureExt());
        params.put("humidityExt", testPlanDetailEntity.getHumidityExt());
        params.put("meantemperature", testPlanDetailEntity.getMeantemperature());
        params.put("meanhumidity", testPlanDetailEntity.getMeanhumidity());
        params.put("populate", "instrument,device");//展开测试，被测对象，并返回
        Observable<TestPlanDetailEntity> observable = NetManager.getInstance().getApiService().stopTestPlan(testPlanId, params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<TestPlanDetailEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(TestPlanDetailEntity testPlanDetailEntity1) {
                getiView().hideProgress();
                getiView().stopSuccess(testPlanDetailEntity1);
            }
        });
    }

    @Override
    public void queryTestPlanDetailData(int testPlanId) {
        Map<String, String> params = new HashMap<>();
        params.put("where", "{\"testplan\":\"" + testPlanId + "\"}");
        params.put("limit", "10");
        Observable<TestDataEntity> observable = NetManager.getInstance().getApiService().queryTestPlanDetailData(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<TestDataEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(TestDataEntity testDataEntity) {
                getiView().queryDetailsDatas(testDataEntity);
            }
        });
    }

    @Override
    public void queryTestPlanDetailDataPage(int testPlanId, int skipCount) {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("where", "{\"testplan\":\"" + testPlanId + "\"}");
        params.put("limit", "10");
        params.put("skip", "" + skipCount);
        Observable<TestDataEntity> observable = NetManager.getInstance().getApiService().queryTestPlanDetailData(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<TestDataEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(TestDataEntity testDataEntity) {
                getiView().hideProgress();
                getiView().queryDetailsDatasPage(testDataEntity);
            }
        });
    }
}
