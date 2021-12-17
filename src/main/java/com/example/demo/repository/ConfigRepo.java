package com.example.demo.repository;

import com.example.demo.entity.Config;

import java.util.List;

public interface ConfigRepo {
    Config getByKey(String key);

    List<Config> getAll();
}
