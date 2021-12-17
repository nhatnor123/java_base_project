package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.dto.ResponseObject;
import com.example.demo.dto.request.config.GetByKeyReq;
import com.example.demo.entity.Config;
import com.example.demo.service.ConfigService;
import com.example.demo.utils.JSONFactory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @ApiOperation(value = "get config by key")
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseObject<Config>> list(@Valid @RequestBody GetByKeyReq req) {
        log.debug(Constant.PREFIX_LOG + " list AliasUser: " + JSONFactory.toString(req));
        ResponseObject<Config> res = configService.getByKey(req.getKey());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
