package com.gs.keystore.storage;


import android.text.TextUtils;
import android.util.LruCache;

/**
 * 字符串缓存的工具类
 * create on 2018-08-30   13:53
 *
 * @author husky
 */
public class MyLruCacheUtils {
    /**
     * 默认30分钟
     */
    public static long defaultDuring = 30 * 60 * 1000;
    public static long TWELEVE = 12 * 60 * 1000;
    /**
     * 一天
     */
    public static long ONE_DAY = 24 * 60 * 60 * 1000;
    private final LruCache<String, CacheItem<String>> mLruCache;
    private static volatile MyLruCacheUtils instance;

    private MyLruCacheUtils() {
        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置缓存的大小
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache<>(cacheSize);

    }

    public static MyLruCacheUtils getInstance() {
        if (instance == null) {
            synchronized (MyLruCacheUtils.class) {
                if (instance == null) {
                    instance = new MyLruCacheUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 把字符串存入缓存 并设置过期时间
     *
     * @param key         存取的关键字
     * @param value       存取的字符串
     * @param expiredTime 过期时间
     */
    public void put(String key, String value, long expiredTime) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            return;
        }
        if (expiredTime <= 0) {
            expiredTime = defaultDuring;
        }
        long time = System.currentTimeMillis();
        mLruCache.put(key, new CacheItem(value, (time + expiredTime)));
    }

    /**
     * 把字符串存入缓存
     *
     * @param key   存取的关键字
     * @param value 存取的字符串
     */
    public void put(String key, String value) {
        put(key, value, defaultDuring);
    }

    /**
     * 获取缓存的字符串
     *
     * @param key 字符串存取的关键字
     * @return 获取缓存的字符串
     */
    public String get(String key) {
        CacheItem<String> cacheItem = (CacheItem<String>) mLruCache.get(key);
        if (null != cacheItem) {
            if (cacheItem.deleteTime - System.currentTimeMillis() > 0) {
                return cacheItem.value;
            } else {
                mLruCache.remove(key);
                return null;
            }
        }
        return null;
    }

    /**
     * 获取需要的字符串，并且自动给它延长默认的过期时间
     *
     * @param key 关键字
     * @return 缓存起来的字符串
     */
    public String getValue(String key) {
        return getValue(key, defaultDuring);
    }

    /**
     * 获取需要的字符串，并进行续命
     *
     * @param key         关键字
     * @param expiredTime 续命时长，如果小于等于0的续默认时长
     * @return 缓存起来的字符串
     */
    public String getValue(String key, long expiredTime) {
        CacheItem<String> cacheItem = (CacheItem<String>) mLruCache.get(key);
        if (null != cacheItem) {
            if (cacheItem.deleteTime - System.currentTimeMillis() > 0) {
                put(key, cacheItem.value, expiredTime);
                return cacheItem.value;
            } else {
                mLruCache.remove(key);
                return null;
            }
        }
        return null;
    }

    /**
     * 把字符串移除缓存
     *
     * @param key 存取的关键字
     */
    public void remove(String key) {
        if (!TextUtils.isEmpty(key)) {
            mLruCache.remove(key);
        }
    }
}
