package com.regongzaixian.jiankong.login.presenter;

import com.regongzaixian.jiankong.login.frame.ILoginPresenter;
import com.regongzaixian.jiankong.login.frame.ILoginView;
import com.regongzaixian.jiankong.model.UserEntity;
import com.regongzaixian.jiankong.net.NetManager;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:37
 * Description:
 */
public class LoginPresenterImpl extends ILoginPresenter {

    @Override
    public void attachView(ILoginView iView) {
        super.attachView(iView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void doLogin(String userName, String pwd) {
        getiView().showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("identifier", userName);
        params.put("password", pwd);
        Observable<UserEntity> observable = NetManager.getInstance().getApiService().login(params);
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
                getiView().loginSuccess(userEntity);
            }
        });

    }
}
