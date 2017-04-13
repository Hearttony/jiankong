package com.regongzaixian.jiankong.base;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:38
 * Description:
 */
public class XJApp extends Application {

    private static Application mAppContext;

    public static Application getInstance() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;

        PgyCrashManager.register(this);
    }
}
