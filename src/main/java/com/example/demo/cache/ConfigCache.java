package com.example.demo.cache;

import com.example.demo.entity.Config;
import com.example.demo.repository.ConfigRepo;
import io.micrometer.core.instrument.util.StringUtils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

public class ConfigCache extends FCache<String, String> {

    private static volatile ConfigCache instance;
    private final int DEFAULT_SIZE = 100;  // record
    private final int DEFAULT_EXPIRE_TIME = 60 * 60; // second

    private final ConfigRepo configRepo;

    private ConfigCache(ConfigRepo configRepo) {
        this.data = new HashMap<>();
        this.size = DEFAULT_SIZE;
        this.configRepo = configRepo;
    }

    public static ConfigCache getInstance(ConfigRepo configRepo) {
        if (instance == null) {
            synchronized (ConfigCache.class) {
                if (instance == null) {
                    instance = new ConfigCache(configRepo);
                }
            }
        }
        return instance;
    }

    public String get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String data = super.get(key);
        if (data == null) {
            return reload(key);
        }
        return data;
    }

    public String reload(String key) {
        Config config = configRepo.getByKey(key);

        if (config != null && config.getValue() != null) {
            super.put(key, config.getValue(), ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(DEFAULT_EXPIRE_TIME).toInstant().toEpochMilli());
            return config.getValue();
        }
        return null;
    }

    public void reloadAll() {
        super.clear();
        List<Config> config = configRepo.getAll();
        config.forEach(c -> super.put(
                c.getKey(), c.getValue(), ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(DEFAULT_EXPIRE_TIME).toInstant().toEpochMilli())
        );
    }

}
