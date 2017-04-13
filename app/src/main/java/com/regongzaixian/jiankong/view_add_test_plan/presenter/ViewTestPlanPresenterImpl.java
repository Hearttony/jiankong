package com.regongzaixian.jiankong.view_add_test_plan.presenter;

import android.content.Intent;

import com.regongzaixian.jiankong.model.TestPlanGroup;
import com.regongzaixian.jiankong.net.NetManager;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanPresenter;
import com.regongzaixian.jiankong.view_add_test_plan.frame.IViewTestPlanView;
import com.regongzaixian.jiankong.view_add_test_plan.view.ViewTestPlanDetailActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:31
 * Description:
 */
public class ViewTestPlanPresenterImpl extends IViewTestPlanPresenter {

    @Override
    public void attachView(IViewTestPlanView iView) {
        super.attachView(iView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void queryTestPlan() {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("sort", "createdAt DESC");
        params.put("populate", "device,instrument");
        Observable<List<TestPlanGroup>> observable = NetManager.getInstance().getApiService().queryTestPlan(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<List<TestPlanGroup>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(List<TestPlanGroup> testPlanEntities) {
                getiView().hideProgress();
                getiView().querySuccess(testPlanEntities);
            }
        });
    }

    @Override
    public void goTestPlanDetail(int testPlanId) {
        Intent intent = new Intent(getiView().getContext(), ViewTestPlanDetailActivity.class);
        intent.putExtra("testPlanId", testPlanId);
        getiView().getActivity().startActivityForResult(intent, 0);
    }
}
