package com.hyhomelab.jexpression.utils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class TimesEntry<T>{
    private final WeakReference<T> value;
    private int times;

    public TimesEntry(T value) {
        this.value = new WeakReference<>(value);
        this.times = 0;
    }

    public T getValue() {
        return value.get();
    }

    public int getTimes() {
        return times;
    }

    public void incrementTimes() {
        this.times++;
    }
}

public class WeakCache<T> {

    private final int capacity;
    private final Map<String, TimesEntry<T>> cache = new HashMap<>();
    private final ReadWriteLock lock =new ReentrantReadWriteLock();

    public WeakCache(int capacity){
        this.capacity = capacity;
    }

    public T get(String key) {
        var entry =  this.cache.get(key);
        if (entry != null) {
            try{
                this.lock.writeLock().lock();
                if(entry.getValue() == null){
                    // 弱引用失效
                    this.cache.remove(key);
                    return null;
                }
                entry.incrementTimes();
                return entry.getValue();
            }finally {
                this.lock.writeLock().unlock();
            }
        }
        return null;
    }

    public void put(String key, T value) {
        try{
            lock.writeLock().lock();
            if(this.cache.containsKey(key)){
                this.cache.put(key, new TimesEntry<>(value));
                return;
            }
            if (this.cache.size() > this.capacity){
                // 找到访问次数最少的数据
                int minVisitedTimes = Integer.MAX_VALUE;
                String minVisitedKey = "";
                for (Map.Entry<String, TimesEntry<T>> entry : this.cache.entrySet()) {
                    if (entry.getValue().getTimes() < minVisitedTimes) {
                        minVisitedTimes = entry.getValue().getTimes();
                        minVisitedKey = entry.getKey();
                    }
                }
                this.cache.remove(minVisitedKey);
            }
            this.cache.put(key, new TimesEntry<>(value));
        }finally{
            lock.writeLock().unlock();
        }
    }
}
