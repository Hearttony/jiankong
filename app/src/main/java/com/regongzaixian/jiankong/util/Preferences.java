package com.regongzaixian.jiankong.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.regongzaixian.jiankong.base.XJApp;
import com.regongzaixian.jiankong.net.NetManager;


/**
 * Author: tony(110618445@qq.com)
 * Date: 2017/4/4
 * Time: 下午3:39
 * Description:
 */
public class Preferences {

    private static final String PREFERENCE_XUNJIAN = "xunjian";
    private static Preferences mInstance;
    private final Context mContext;
    private SharedPreferences mPrefs;

    public static Preferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Preferences(context);
        }

        return mInstance;
    }

    public static Preferences getInstance() {
        return getInstance(XJApp.getInstance());
    }

    private Preferences(Context context) {
        mContext = context;
        doLoadPrefs();
    }

    public void doLoadPrefs() {
        mPrefs = mContext.getSharedPreferences(PREFERENCE_XUNJIAN,
                Activity.MODE_PRIVATE);

    }


    public void storeToken(String token) {
        if (mPrefs == null) {
            mPrefs = mContext.getSharedPreferences(PREFERENCE_XUNJIAN, 0);
        }
        SharedPreferences.Editor prefEditor = mPrefs.edit();
        prefEditor.putString("token", token);
        prefEditor.commit();

        NetManager.getInstance().refreshRetrofit();
    }

    public String getToken() {
        if (mPrefs == null) {
            mPrefs = mContext.getSharedPreferences(PREFERENCE_XUNJIAN, 0);
        }
        return mPrefs.getString("token", "");

    }

    public void storeEmail(String email) {
        if (mPrefs == null) {
            mPrefs = mContext.getSharedPreferences(PREFERENCE_XUNJIAN, 0);
        }
        SharedPreferences.Editor prefEditor = mPrefs.edit();
        prefEditor.putString("email", email);
        prefEditor.commit();
    }

    public String getEmail() {
        if (mPrefs == null) {
            mPrefs = mContext.getSharedPreferences(PREFERENCE_XUNJIAN, 0);
        }
        return mPrefs.getString("email", "");
    }

    public void storeBakEmail(String emailTo) {
        if (mPrefs == null) {
            mPrefs = mContext.getSharedPreferences(PREFERENCE_XUNJIAN, 0);
        }
        SharedPreferences.Editor prefEditor = mPrefs.edit();
        prefEditor.putString("bakEmail", emailTo);
        prefEditor.commit();
    }

    public String getBakEmail() {
        if (mPrefs == null) {
            mPrefs = mContext.getSharedPreferences(PREFERENCE_XUNJIAN, 0);
        }
        return mPrefs.getString("bakEmail", "");
    }
}
