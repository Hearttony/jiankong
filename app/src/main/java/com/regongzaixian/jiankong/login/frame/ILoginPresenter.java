package com.regongzaixian.jiankong.login.frame;

import com.regongzaixian.jiankong.mvp.BasePresenter;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/3/28
 * Time: 下午9:56
 * Description:
 */
public abstract class ILoginPresenter extends BasePresenter<ILoginView> {

    public abstract void doLogin(String userName, String pwd);
}
