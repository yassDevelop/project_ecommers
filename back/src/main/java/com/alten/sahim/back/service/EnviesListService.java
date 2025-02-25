package com.alten.sahim.back.service;


import com.alten.sahim.back.dto.EnviesListDto;
import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.dto.UserDto;
import com.alten.sahim.back.entity.Cart;
import com.alten.sahim.back.entity.EnviesList;
import com.alten.sahim.back.entity.Product;
import com.alten.sahim.back.entity.User;

public interface EnviesListService {
    public EnviesListDto getEnviesListByUser(UserDto userDto);

    public EnviesListDto addProductToEnviesList(UserDto userDto, ProductDto productDto);

    public EnviesListDto removeProductFromEnviesList(UserDto userDto, ProductDto productDto);
}