package com.regongzaixian.jiankong.instrument_mgr.frame;

import com.regongzaixian.jiankong.model.InstrumentEntity;
import com.regongzaixian.jiankong.mvp.BaseView;

import java.util.List;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:34
 * Description:
 */
public interface IInstrumentMgrView extends BaseView {
    void querySuccess(List<InstrumentEntity> devices);

    void queryPageSuccess(List<InstrumentEntity> devices);

    void startModify(InstrumentEntity deviceEntity);
}
