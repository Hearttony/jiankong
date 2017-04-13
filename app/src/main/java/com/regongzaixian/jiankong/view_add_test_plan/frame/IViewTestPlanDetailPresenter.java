package com.regongzaixian.jiankong.view_add_test_plan.frame;

import com.regongzaixian.jiankong.model.TestPlanDetailEntity;
import com.regongzaixian.jiankong.mvp.BasePresenter;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:31
 * Description:
 */
public abstract class IViewTestPlanDetailPresenter extends BasePresenter<IViewTestPlanDetailView> {
    public abstract void queryTestPlanDetail(int testPlanId);

    public abstract void deleteTestPlan(int testPlanId);

    public abstract void sendEmail(int testPlanId, String emailTo);

    public abstract void stopTestPlan(int testPlanId, TestPlanDetailEntity testPlanDetailEntity);

    public abstract void queryTestPlanDetailData(int testPlanId);

    public abstract void queryTestPlanDetailDataPage(int testPlanId, int skipCount);
}
