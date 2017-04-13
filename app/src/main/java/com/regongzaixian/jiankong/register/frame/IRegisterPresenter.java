package com.regongzaixian.jiankong.register.frame;

import com.regongzaixian.jiankong.mvp.BasePresenter;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:35
 * Description:
 */
public abstract class IRegisterPresenter extends BasePresenter<IRegisterView> {
    public abstract void register(String email, String pwd);
}
