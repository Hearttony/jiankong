package com.regongzaixian.jiankong.mvp;

import android.app.Activity;
import android.content.Context;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:36
 * Description:
 */
public interface BaseView extends IView {
    /**
     * 获取当前上下文
     *
     * @return
     */
    Context getContext();

    Activity getActivity();

}
