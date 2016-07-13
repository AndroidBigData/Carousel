package com.zhao.carousel;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/7/13.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Xutils相关
        x.Ext.init(MyApplication.this);
        x.Ext.setDebug(true);
    }
}
