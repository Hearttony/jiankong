package com.regongzaixian.jiankong.view_add_test_plan.frame;

import com.regongzaixian.jiankong.model.TestPlanGroup;
import com.regongzaixian.jiankong.mvp.BaseView;

import java.util.List;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:31
 * Description:
 */
public interface IViewTestPlanView extends BaseView {
    void querySuccess(List<TestPlanGroup> testPlanEntities);
}
