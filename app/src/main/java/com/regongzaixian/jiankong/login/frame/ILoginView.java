package com.regongzaixian.jiankong.login.frame;

import com.regongzaixian.jiankong.model.UserEntity;
import com.regongzaixian.jiankong.mvp.BaseView;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:37
 * Description:
 */
public interface ILoginView extends BaseView {
    void loginSuccess(UserEntity userEntity);
}
