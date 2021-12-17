package com.example.demo.repository.impl;

import com.example.demo.entity.Config;
import com.example.demo.repository.ConfigRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConfigRepoImpl implements ConfigRepo {

    @Override
    public Config getByKey(String key) {
        return new Config();
    }

    @Override
    public List<Config> getAll() {
        return new ArrayList<>();
    }
}
