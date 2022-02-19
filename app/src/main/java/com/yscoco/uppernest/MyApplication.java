package com.yscoco.uppernest;

import android.app.Activity;

import com.squareup.leakcanary.LeakCanary;
import com.yscoco.uppernest.commonlibrary.base.activity.BaseActivity;
import com.yscoco.uppernest.commonlibrary.global.GlobalApplication;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.external.ExternalAdaptInfo;
import me.jessyan.autosize.onAdaptListener;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class MyApplication extends GlobalApplication {

    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initThreeService();
    }

    private void initThreeService() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        AutoSizeConfig.getInstance()
                .setCustomFragment(true)
                .setOnAdaptListener(new onAdaptListener() {
                    @Override
                    public void onAdaptBefore(Object target, Activity activity) {

                    }

                    @Override
                    public void onAdaptAfter(Object target, Activity activity) {

                    }
                });

        AutoSize.initCompatMultiProcess(this);
        customAdaptForExternal();

    }

    private void customAdaptForExternal() {
        AutoSizeConfig.getInstance().getExternalAdaptManager()
                .addExternalAdaptInfoOfActivity(BaseActivity.class, new ExternalAdaptInfo(true, 400));
    }

}
