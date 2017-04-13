package com.regongzaixian.jiankong.register.frame;

import com.regongzaixian.jiankong.model.UserEntity;
import com.regongzaixian.jiankong.mvp.BaseView;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:35
 * Description:
 */
public interface IRegisterView extends BaseView {
    void registerSuccess(UserEntity userEntity);
}
