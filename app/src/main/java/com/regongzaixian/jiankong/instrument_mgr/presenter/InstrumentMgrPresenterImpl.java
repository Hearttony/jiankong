package com.regongzaixian.jiankong.instrument_mgr.presenter;

import com.regongzaixian.jiankong.model.InstrumentEntity;
import com.regongzaixian.jiankong.net.NetManager;
import com.regongzaixian.jiankong.instrument_mgr.frame.IInstrumentMgrPresenter;
import com.regongzaixian.jiankong.instrument_mgr.frame.IInstrumentMgrView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:34
 * Description:
 */
public class InstrumentMgrPresenterImpl extends IInstrumentMgrPresenter {
    public static final int PageLimitCount = 8;

    @Override
    public void attachView(IInstrumentMgrView iView) {
        super.attachView(iView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void queryInstruments() {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("sort", "id desc");
        params.put("limit", "" + PageLimitCount);//返回前8条数据
        Observable<List<InstrumentEntity>> observable = NetManager.getInstance().getApiService().queryInstruments(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<List<InstrumentEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(List<InstrumentEntity> devices) {
                getiView().hideProgress();
                getiView().querySuccess(devices);
            }
        });
    }

    @Override
    public void queryInstrumentsPage(int currentLastItemIndex) {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("sort", "id desc");
        params.put("skip", "" + (currentLastItemIndex + 1));
        params.put("limit", "" + PageLimitCount);
        Observable<List<InstrumentEntity>> observable = NetManager.getInstance().getApiService().queryInstrumentsForPage(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<List<InstrumentEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(List<InstrumentEntity> devices) {
                getiView().hideProgress();
                getiView().queryPageSuccess(devices);
            }
        });
    }

    @Override
    public void gotoModifyDetail(InstrumentEntity instrumentEntity) {
        getiView().startModify(instrumentEntity);
    }
}
