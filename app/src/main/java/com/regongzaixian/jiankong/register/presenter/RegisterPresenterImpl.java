package com.regongzaixian.jiankong.register.presenter;

import com.regongzaixian.jiankong.model.UserEntity;
import com.regongzaixian.jiankong.net.NetManager;
import com.regongzaixian.jiankong.register.frame.IRegisterPresenter;
import com.regongzaixian.jiankong.register.frame.IRegisterView;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:35
 * Description:
 */
public class RegisterPresenterImpl extends IRegisterPresenter {

    @Override
    public void attachView(IRegisterView iView) {
        super.attachView(iView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void register(String email, String pwd) {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("username", email);
        params.put("email", email);
        params.put("password", pwd);
        Observable<UserEntity> observable = NetManager.getInstance().getApiService().register(params);
        NetManager.getInstance().runRxJava(observable, new Subscriber<UserEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getiView().hideProgress();
                getiView().toast(e.getMessage());
            }

            @Override
            public void onNext(UserEntity userEntity) {
                getiView().hideProgress();
                getiView().registerSuccess(userEntity);
            }
        });
    }
}
