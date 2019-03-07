package com.gs.keystore.storage;

/**
 * create by husky
 * create on 2018-08-30   14:23
 */
public class CacheItem<V> {
    public V value;
    /**
     * 过期时间
     */
    public long deleteTime;

    public CacheItem(V value, long deleteTime) {
        this.value = value;
        this.deleteTime = deleteTime;
    }
}
