package com.gs.keystoredemo;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.gs.keystore.Config;
import com.gs.keystore.basic.BaseApplication;
import com.gs.keystore.keystore.KeyStoreHelper;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author husky
 * create on 2019/3/7-15:39
 */
public class MyApp extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        long start = System.currentTimeMillis();
        Log.d(Config.GLOBAL_TAG, "keystore初始化开始：" + String.valueOf(start));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            initKeyStore();
        }
        long end = System.currentTimeMillis();
        Log.d(Config.GLOBAL_TAG, "keystore初始化结束：" + String.valueOf(end));
        Log.d(Config.GLOBAL_TAG, "keystore初始化耗时：" + String.valueOf(end - start));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initKeyStore() {
        if (!KeyStoreHelper.isHaveKeyStore()) {
            try {
                KeyStoreHelper.createKeys();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

}
