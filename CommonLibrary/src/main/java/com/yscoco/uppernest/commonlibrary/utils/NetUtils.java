package com.yscoco.uppernest.commonlibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class NetUtils {

    /**
     * 没有连接到网络
     */
    public static final int NETWORK_NONE = -1;

    /**
     * 移动网络
     */
    public static final int NETWORK_MOBILE = 0;

    /**
     * 无线网络
     */
    public static final int NETWORK_WIFI = 1;

    public static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static int getNetWorkState() {
        if (mContext == null) {
            throw new UnsupportedOperationException("please use NetUtils before init it");
        }
        //得到连接管理器对象
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取所有网络连接的信息
        Network[] networks = connMgr.getAllNetworks();
        for (int i = 0; i < networks.length; i++) {
            //获取ConnectivityManager对象对应的NetWorkInfo对象
            NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
            if (networkInfo.isConnected()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return NETWORK_MOBILE;
                } else {
                    return NETWORK_WIFI;
                }
            }
        }
        return NETWORK_NONE;
    }

}
