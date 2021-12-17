package com.example.demo.service;

import com.example.demo.dto.ResponseObject;
import com.example.demo.entity.Config;

public interface ConfigService {
    ResponseObject<Config> getByKey(String key);

}
