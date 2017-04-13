package com.regongzaixian.jiankong.data_query.frame;

import com.regongzaixian.jiankong.mvp.BasePresenter;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:38
 * Description:
 */
public abstract class IDataQueryPresenter extends BasePresenter<IDataQueryView> {
    public abstract void queryLastDayTestPlan();

    public abstract void goTestPlanDetail(int testPlanId);
}
