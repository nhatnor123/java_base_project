package com.example.demo.cache;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;

public abstract class FCache<K, V> {
    public HashMap<K, Data<V>> data;
    protected int size;

    public FCache() {
    }

    public V get(K key) {
        if (!this.data.containsKey(key)) {
            return null;
        } else {
            Data<V> d = this.data.get(key);
            if (d != null && d.getData() != null && d.getExpiredTime() >= ZonedDateTime.now(ZoneOffset.UTC).toInstant().toEpochMilli()) {
                return d.getData();
            } else {
                if (d != null) {
                    this.data.remove(key);
                }

                return null;
            }
        }
    }

    public void put(K key, V value, long expired) {
        if (this.data.size() == this.size) {
            this.data.clear();
        }

        this.data.put(key, new Data<>(value, expired));
    }

    public void clear() {
        this.data.clear();
    }

    public static class Data<V> {
        private V data;
        private final long expiredTime;

        public Data(V data, long expiredTime) {
            this.data = data;
            this.expiredTime = expiredTime;
        }

        public V getData() {
            return this.data;
        }

        public void setData(V data) {
            this.data = data;
        }

        long getExpiredTime() {
            return this.expiredTime;
        }
    }
}
