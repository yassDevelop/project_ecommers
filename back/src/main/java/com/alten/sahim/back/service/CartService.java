package com.alten.sahim.back.service;


import com.alten.sahim.back.dto.CartDto;
import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.dto.UserDto;
import com.alten.sahim.back.entity.Cart;
import com.alten.sahim.back.entity.Product;
import com.alten.sahim.back.entity.User;

public interface CartService {
    public CartDto getCartByUser(UserDto userDto);

    public CartDto addProductToCart(UserDto userDto, ProductDto productDto);

    public CartDto removeProductFromCart(UserDto userDto, ProductDto productDto);
}