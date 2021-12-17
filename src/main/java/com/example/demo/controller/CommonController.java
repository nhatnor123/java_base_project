package com.example.demo.controller;

import com.example.demo.cache.ConfigCache;
import com.example.demo.constant.Constant;
import com.example.demo.dto.ResponseObject;
import com.example.demo.enums.ResponseStatus;
import com.example.demo.repository.ConfigRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/common")
public class CommonController {

    public final ConfigRepo configRepo;

    public CommonController(ConfigRepo configRepo) {
        this.configRepo = configRepo;
    }

    @GetMapping(value = "/force-reload-cache")
    public ResponseEntity<ResponseObject<?>> forceReloadCache() {
        log.debug(Constant.PREFIX_LOG + " force Reload Cache ");
        ConfigCache.getInstance(configRepo).reloadAll();
        return new ResponseEntity<>(new ResponseObject<>(true, ResponseStatus.SUCCESSFUL), HttpStatus.OK);
    }

}
