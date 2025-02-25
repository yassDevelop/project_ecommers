package com.alten.sahim.back.service.impl;

import com.alten.sahim.back.dao.CartDao;
import com.alten.sahim.back.dto.CartDto;
import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.dto.UserDto;
import com.alten.sahim.back.entity.Cart;
import com.alten.sahim.back.entity.Product;
import com.alten.sahim.back.entity.User;
import com.alten.sahim.back.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    public CartDto getCartByUser(UserDto userDto) {
        Cart cart = cartDao.findByUser(UserDto.toEntity(userDto));
        return CartDto.toDTO((cart));
    }

    public CartDto addProductToCart(UserDto userDto, ProductDto productDto) {
        CartDto cartDto = getCartByUser(userDto);
        cartDto.getListProductDto().add(productDto);
        Cart cartSave = cartDao.save(CartDto.toEntity(cartDto));
        return CartDto.toDTO(cartSave);
    }

    public CartDto removeProductFromCart(UserDto userDto, ProductDto productDto) {
        CartDto cartDto = getCartByUser(userDto);
        cartDto.getListProductDto().remove(productDto);
        Cart cartRemove = cartDao.save(CartDto.toEntity(cartDto));
        return CartDto.toDTO(cartRemove);
    }
}