package com.yscoco.uppernest.commonlibrary.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.yscoco.uppernest.commonlibrary.Constant;


/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class NetworkBroadcastReceiver extends BroadcastReceiver {

//    private BaseRootActivity

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constant.NETBROADCAST)) {
            NetUtils.init(context);
            int netWorkState = NetUtils.getNetWorkState();

        }
    }

    public interface NetEvent {

        void onNetChange(int netWorkState);

    }

}
