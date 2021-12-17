package com.example.demo.service.impl;

import com.example.demo.dto.MetaDTO;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.ResponseObject;
import com.example.demo.dto.SortDTO;
import com.example.demo.entity.Config;
import com.example.demo.enums.ResponseStatus;
import com.example.demo.enums.SortEnum;
import com.example.demo.repository.ConfigRepo;
import com.example.demo.service.ConfigService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ConfigServiceImpl implements ConfigService {
    public final ConfigRepo configRepo;

    public ConfigServiceImpl(ConfigRepo configRepo) {
        this.configRepo = configRepo;
    }

    @Override
    public ResponseObject<Config> getByKey(String key) {
        Config config = configRepo.getByKey(key);

        return new ResponseObject<>(
                true,
                ResponseStatus.SUCCESSFUL,
                config,
                MetaDTO.builder().pagination(
                        PaginationDTO.builder().page(1).limit(10).total(1L).build()
                ).sort(Collections.singletonList(new SortDTO(SortEnum.ASC.name(), "id"))).build()
        );
    }

}
