package io.github.lvbo.learn.java.concurrent.lock.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-11 09:06
 */
public class Cache<K, V> {
    private final Map<K, V> map = new HashMap<K, V>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock rLock = readWriteLock.readLock();
    private Lock wLock = readWriteLock.writeLock();

    public void put(K key, V value) {
        wLock.lock();
        try {
            map.put(key, value);
        } finally {
            wLock.unlock();
        }
    }

    public V get(K key) {
        rLock.lock();
        try {
            return map.get(key);
        } finally {
            rLock.unlock();
        }
    }
}
