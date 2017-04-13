package com.regongzaixian.jiankong.device_mgr.frame;

import com.regongzaixian.jiankong.model.DeviceEntity;
import com.regongzaixian.jiankong.mvp.BasePresenter;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:33
 * Description:
 */
public abstract class IDeviceMgrPresenter extends BasePresenter<IDeviceMgrView> {
    public abstract void queryDevices();

    public abstract void queryDevicePage(int currentLastItemIndex);

    public abstract void gotoModifyDetail(DeviceEntity deviceEntity);
}
