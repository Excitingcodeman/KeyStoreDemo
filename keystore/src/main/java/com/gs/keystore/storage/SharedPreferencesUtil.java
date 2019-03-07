package com.gs.keystore.storage;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.gs.keystore.basic.BaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husky
 * create on 2018/11/21-10:28
 * <p>
 * 数据存储的封装   请不要直接用这个  ，请使用带keystroe加密的处理
 */
public class SharedPreferencesUtil {
    private static SharedPreferences sharedPreferences;

    private static String sharedPreferencesName = "MaMiSharedPreferencesName";

    public static final Gson gson = new Gson();


    private static volatile SharedPreferencesUtil instance;

    private SharedPreferencesUtil() {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getInstance().getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
        }
    }

    public static SharedPreferencesUtil getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtil();
                }
            }
        }
        return instance;
    }


    /**
     * 存入Boolean类型的值
     *
     * @param key   key值
     * @param value 需要存的值
     */
    public void putBoolean(String key, boolean value) {

        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 获取 Boolean类型的值
     *
     * @param key      key值
     * @param defValue 默认的值
     * @return 默认值或者此节点读取到的结果
     */
    public boolean getBoolean(String key, boolean defValue) {

        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 写入String类型的值
     *
     * @param key   key值
     * @param value 需要存的值
     */
    public void putString(String key, String value) {

        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * 获取String类型的值
     *
     * @param key      key值
     * @param defValue 默认的值
     * @return 默认值或者此节点读取到的结果
     */
    public String getString(String key, String defValue) {

        return sharedPreferences.getString(key, defValue);
    }

    /**
     * 写入int类型的数据
     *
     * @param key   key值
     * @param value 需要存的值
     */
    public void putInt(String key, int value) {

        sharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * 获取int类型的数据
     *
     * @param key      key值
     * @param defValue 默认的值
     * @return 默认值或者此节点读取到的结果
     */
    public int getInt(String key, int defValue) {

        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * 写入long类型的数据
     *
     * @param key   key值
     * @param value 需要存的值
     */
    public void putLong(String key, long value) {

        sharedPreferences.edit().putLong(key, value).apply();
    }

    /**
     * 获取long类型的数据
     *
     * @param key      key值
     * @param defValue 默认的值
     * @return 默认值或者此节点读取到的结果
     */
    public long getLong(String key, long defValue) {

        return sharedPreferences.getLong(key, defValue);
    }

    /**
     * 写入float类型的数据
     *
     * @param key   key值
     * @param value 需要存的值
     */
    public void putFloat(String key, float value) {

        sharedPreferences.edit().putFloat(key, value).apply();
    }

    /**
     * 获取float类型的数据
     *
     * @param key      key值
     * @param defValue 默认的值
     * @return 默认值或者此节点读取到的结果
     */
    public float getFloat(String key, float defValue) {

        return sharedPreferences.getFloat(key, defValue);
    }

    /**
     * 移除指定的节点
     *
     * @param key 指定的节点
     */
    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    /**
     * 保存List对象到sp中
     *
     * @param key      指定的节点
     * @param dataList 数据集合
     * @param <T>      对象的类型
     */
    public <T> void setDataList(String key, List<T> dataList) {
        if (null == dataList || dataList.size() <= 0) {
            return;
        }
        String toJson = gson.toJson(dataList);
        putString(key, toJson);
    }

    /**
     * 获取List对象
     *
     * @param key    指定的节点
     * @param tClass 对象类型
     * @param <T>    对象类型
     * @return 返回指定的对象的集合或者空的集合
     */
    public <T> List<T> getDataList(String key, Class<T> tClass) {
        List<T> dataList = new ArrayList<T>();
        String string = getString(key, null);
        if (null == string) {
            return dataList;
        }
        try {
            JsonArray array = new JsonParser().parse(string).getAsJsonArray();
            for (final JsonElement elem : array) {
                dataList.add(gson.fromJson(elem, tClass));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * 存入指定对象到sp
     *
     * @param key    指定的节点
     * @param tClass 对象类型
     * @param <T>    对象类型
     */
    public <T> void saveObject(String key, T tClass) {
        if (null == tClass) {
            return;
        }
        String toJson = gson.toJson(tClass);
        putString(key, toJson);
    }

    /**
     * 获取指定对象
     *
     * @param key    指定的节点
     * @param tClass 对象类型
     * @param <T>    对象类型
     * @return 返回获取的对象或者null
     */
    public <T> T getObject(String key, Class<T> tClass) {
        String string = getString(key, null);
        if (null != string) {
            try {
                return gson.fromJson(string, tClass);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

}
