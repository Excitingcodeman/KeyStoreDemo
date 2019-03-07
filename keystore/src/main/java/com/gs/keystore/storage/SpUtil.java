package com.gs.keystore.storage;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.gs.keystore.keystore.KeyStoreHelper;

/**
 * @author husky
 * create on 2018/11/28-10:48
 * 加密处理存储
 */
public class SpUtil {

    /**
     * 加密存储
     *
     * @param key   key
     * @param value value
     */
    public static void putString(@NonNull String key, @NonNull String value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String encryptValue = KeyStoreHelper.encryptString(value);
            SharedPreferencesUtil.getInstance().putString(key, encryptValue);
        } else {
            SharedPreferencesUtil.getInstance().putString(key, value);
        }
    }

    /**
     * 解密数据
     *
     * @param key key
     * @return
     */
    public static String getString(@NonNull String key) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String value = SharedPreferencesUtil.getInstance().getString(key, "");
            if (TextUtils.isEmpty(value)) {
                return "";
            }
            return KeyStoreHelper.decryptString(value);
        } else {
            return SharedPreferencesUtil.getInstance().getString(key, "");
        }
    }

    public static String getString(@NonNull String key, String defaultValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String value = SharedPreferencesUtil.getInstance().getString(key, "");
            if (TextUtils.isEmpty(value)) {
                return defaultValue;
            }
            String decryptString = KeyStoreHelper.decryptString(value);
            return TextUtils.isEmpty(decryptString) ? defaultValue : decryptString;
        } else {
            return SharedPreferencesUtil.getInstance().getString(key, defaultValue);
        }
    }


    public static void putInt(@NonNull String key, @NonNull int value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String encryptValue = KeyStoreHelper.encryptString(String.valueOf(value));
            SharedPreferencesUtil.getInstance().putString(key, encryptValue);
        } else {
            SharedPreferencesUtil.getInstance().putInt(key, value);
        }
    }

    public static int getInt(@NonNull String key, int defaultValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String value = SharedPreferencesUtil.getInstance().getString(key, "");
            String decryptString = KeyStoreHelper.decryptString(value);
            if (TextUtils.isEmpty(decryptString)) {
                return defaultValue;
            }
            try {
                return Integer.parseInt(decryptString);
            } catch (Exception e) {
                return defaultValue;
            }

        } else {
            return SharedPreferencesUtil.getInstance().getInt(key, defaultValue);
        }
    }


    public static void putBoolean(@NonNull String key, @NonNull boolean value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String encryptValue = KeyStoreHelper.encryptString(String.valueOf(value));
            SharedPreferencesUtil.getInstance().putString(key, encryptValue);
        } else {
            SharedPreferencesUtil.getInstance().putBoolean(key, value);
        }
    }


    public static boolean getBoolean(@NonNull String key, boolean defaultValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String value = SharedPreferencesUtil.getInstance().getString(key, "");
            String decryptString = KeyStoreHelper.decryptString(value);
            if (TextUtils.isEmpty(decryptString)) {
                return defaultValue;
            }
            try {
                return Boolean.parseBoolean(decryptString);
            } catch (Exception e) {
                return defaultValue;
            }

        } else {
            return SharedPreferencesUtil.getInstance().getBoolean(key, defaultValue);
        }
    }


    public static void putFloat(@NonNull String key, @NonNull float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String encryptValue = KeyStoreHelper.encryptString(String.valueOf(value));
            SharedPreferencesUtil.getInstance().putString(key, encryptValue);
        } else {
            SharedPreferencesUtil.getInstance().putFloat(key, value);
        }
    }


    public static float getFloat(@NonNull String key, float defaultValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String value = SharedPreferencesUtil.getInstance().getString(key, "");
            String decryptString = KeyStoreHelper.decryptString(value);
            try {
                return Float.parseFloat(decryptString);
            } catch (Exception e) {
                return defaultValue;
            }

        } else {
            return SharedPreferencesUtil.getInstance().getFloat(key, defaultValue);
        }
    }

    public static void putLong(@NonNull String key, @NonNull long value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String encryptValue = KeyStoreHelper.encryptString(String.valueOf(value));
            SharedPreferencesUtil.getInstance().putString(key, encryptValue);
        } else {
            SharedPreferencesUtil.getInstance().putLong(key, value);
        }
    }


    public static long getFloat(@NonNull String key, long defaultValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String value = SharedPreferencesUtil.getInstance().getString(key, "");
            String decryptString = KeyStoreHelper.decryptString(value);
            try {
                return Long.parseLong(decryptString);
            } catch (Exception e) {
                return defaultValue;
            }

        } else {
            return SharedPreferencesUtil.getInstance().getLong(key, defaultValue);
        }
    }

    public static void remove(@NonNull String key) {
        SharedPreferencesUtil.getInstance().remove(key);
    }
}
