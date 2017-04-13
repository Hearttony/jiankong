package com.regongzaixian.jiankong.mvp;


public interface IView {
    /**
     * toast提示
     *
     * @param str
     */
    void toast(String str);

    /**
     * 显示加载框
     */
    void showProgress();

    /**
     * 隐藏加载框
     */
    void hideProgress();

}
