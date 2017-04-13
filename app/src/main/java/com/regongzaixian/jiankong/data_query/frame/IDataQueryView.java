package com.regongzaixian.jiankong.data_query.frame;

import com.regongzaixian.jiankong.model.TestPlanGroup;
import com.regongzaixian.jiankong.mvp.BaseView;

import java.util.List;

/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:38
 * Description:
 */
public interface IDataQueryView extends BaseView {
    void querySuccess(List<TestPlanGroup> testPlanEntities);
}
