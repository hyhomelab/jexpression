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

class TimeoutEntry<T> extends TimesEntry<T>{

    private final long timeoutMillis;
    private final long createTimeMillis;

    public TimeoutEntry(T value, long timeoutMillis) {
        super(value);
        this.timeoutMillis = timeoutMillis;
        this.createTimeMillis = System.currentTimeMillis();
    }

    private boolean isTimeout(){
        return timeoutMillis > 0 && System.currentTimeMillis() - createTimeMillis > timeoutMillis;
    }

    @Override
    public T getValue() {
        if(isTimeout()){
            return null;
        }
        return super.getValue();
    }
}

public class WeakCache<T> {

    private final int capacity;
    private final long timeoutMillis;
    public static final long Default_Timeout_Millis = 30 * 1000;
    private final Map<String, TimeoutEntry<T>> cache = new HashMap<>();
    private final ReadWriteLock lock =new ReentrantReadWriteLock();

    public WeakCache() {
        this.capacity = 10;
        this.timeoutMillis = Default_Timeout_Millis;
    }

    public WeakCache(int capacity){
        this.capacity = capacity;
        this.timeoutMillis = Default_Timeout_Millis;
    }

    public WeakCache(int capacity, long timeoutMillis){
        this.capacity = capacity;
        this.timeoutMillis = timeoutMillis;
    }

    public void clean(){
        try{
            this.lock.writeLock().lock();
            cache.clear();
        }finally {
            this.lock.writeLock().unlock();
        }
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
                this.cache.put(key, new TimeoutEntry<>(value, this.timeoutMillis));
                return;
            }
            if (this.cache.size() > this.capacity){
                // 找到访问次数最少的数据
                int minVisitedTimes = Integer.MAX_VALUE;
                String minVisitedKey = "";
                for (Map.Entry<String, TimeoutEntry<T>> entry : this.cache.entrySet()) {
                    if (entry.getValue().getTimes() < minVisitedTimes) {
                        minVisitedTimes = entry.getValue().getTimes();
                        minVisitedKey = entry.getKey();
                    }
                }
                this.cache.remove(minVisitedKey);
            }
            this.cache.put(key, new TimeoutEntry<>(value, this.timeoutMillis));
        }finally{
            lock.writeLock().unlock();
        }
    }
}
