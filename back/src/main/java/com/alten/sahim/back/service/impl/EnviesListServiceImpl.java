package com.alten.sahim.back.service.impl;

import com.alten.sahim.back.dao.EnviesListDao;
import com.alten.sahim.back.dto.EnviesListDto;
import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.dto.UserDto;
import com.alten.sahim.back.entity.EnviesList;
import com.alten.sahim.back.service.EnviesListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnviesListServiceImpl implements EnviesListService {
    @Autowired
    private EnviesListDao enviesListDao;

    public EnviesListDto getEnviesListByUser(UserDto userDto) {
        EnviesList enlist = enviesListDao.findByUser(UserDto.toEntity(userDto));
        return EnviesListDto.toDto(enlist);
    }

    public EnviesListDto addProductToEnviesList(UserDto userDto, ProductDto productDto) {
        EnviesListDto enviesListDto = getEnviesListByUser(userDto);
        enviesListDto.getListProductDto().add(productDto);
        EnviesList enviesListSave = enviesListDao.save(EnviesListDto.toEntity(enviesListDto));
        return EnviesListDto.toDto(enviesListSave);
    }

    public EnviesListDto removeProductFromEnviesList(UserDto userDto, ProductDto productDto) {
        EnviesListDto enviesListDto = getEnviesListByUser(userDto);
        enviesListDto.getListProductDto().remove(productDto);
        EnviesList enviesListRemove = enviesListDao.save(EnviesListDto.toEntity(enviesListDto));
        return EnviesListDto.toDto(enviesListRemove);
    }
}