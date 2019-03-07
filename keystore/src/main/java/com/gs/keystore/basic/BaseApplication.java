package com.gs.keystore.basic;

import android.app.Application;

/**
 * @author husky
 * create on 2019/3/7-15:50
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
