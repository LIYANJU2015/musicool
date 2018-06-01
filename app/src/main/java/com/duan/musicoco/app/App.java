package com.duan.musicoco.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.duan.musicoco.preference.SettingPreference;
import com.duan.musicoco.setting.AutoSwitchThemeController;
import com.duan.musicoco.util.FBAdUtils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by DuanJiaNing on 2017/5/25.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        checkAutoThemeSwitch();
        CrashReport.initCrashReport(getApplicationContext());
        FBAdUtils.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void checkAutoThemeSwitch() {
        SettingPreference settingPreference = new SettingPreference(this);
        AutoSwitchThemeController instance = AutoSwitchThemeController.getInstance(this);
        if (settingPreference.autoSwitchNightTheme() && !instance.isSet()) {
            instance.setAlarm();
        } else {
            instance.cancelAlarm();
        }
    }
}
