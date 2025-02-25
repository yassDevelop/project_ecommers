package com.alten.sahim.back.service;


import com.alten.sahim.back.dto.CartDto;
import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.dto.UserDto;

public interface CartService {
    public CartDto getCartByUser(UserDto userDto);

    public CartDto addProductToCart(UserDto userDto, ProductDto productDto);

    public CartDto removeProductFromCart(UserDto userDto, ProductDto productDto);
}