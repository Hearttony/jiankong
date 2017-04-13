package com.regongzaixian.jiankong.view_add_test_plan.frame;

import com.regongzaixian.jiankong.mvp.BasePresenter;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:31
 * Description:
 */
public abstract class IViewTestPlanPresenter extends BasePresenter<IViewTestPlanView> {
    public abstract void queryTestPlan();

    public abstract void goTestPlanDetail(int testPlanId);
}
