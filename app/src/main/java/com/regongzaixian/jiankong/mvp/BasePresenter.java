package com.regongzaixian.jiankong.mvp;

public class BasePresenter<V extends IView> implements Presenter<V> {

    private V iView;

    public V getiView() {
        return iView;
    }

    @Override
    public void attachView(V iView) {
        this.iView = iView;
    }

    @Override
    public void detachView() {
        iView = null;
    }

}
