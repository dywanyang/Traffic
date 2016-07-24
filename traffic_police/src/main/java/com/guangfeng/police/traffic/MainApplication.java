package com.guangfeng.police.traffic;


import android.app.Application;

import com.guangfeng.police.traffic.mode.TrafficModel;

/**
 * by heyongjian
 * on 15/9/9
 */
public class MainApplication extends Application {
    public static TrafficModel sTrafficModel;
    @Override
    public void onCreate() {
        super.onCreate();
        sTrafficModel = new TrafficModel(getApplicationContext());
    }

}
