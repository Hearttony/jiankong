package com.regongzaixian.jiankong.view_add_test_plan.frame;

import com.regongzaixian.jiankong.model.TestDataEntity;
import com.regongzaixian.jiankong.model.TestPlanDetailEntity;
import com.regongzaixian.jiankong.mvp.BaseView;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:31
 * Description:
 */
public interface IViewTestPlanDetailView extends BaseView {
    void queryDetailSuccess(TestPlanDetailEntity testPlanDetailEntity);

    void deleteTestPlanSuccess();

    void stopSuccess(TestPlanDetailEntity testPlanDetailEntity1);

    void queryDetailsDatas(TestDataEntity testDataEntity);

    void queryDetailsDatasPage(TestDataEntity testDataEntity);
}
