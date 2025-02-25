package com.alten.sahim.back.controller;

import com.alten.sahim.back.dto.CartDto;
import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.dto.UserDto;
import com.alten.sahim.back.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public CartDto getCart(@AuthenticationPrincipal UserDto userDto) {
        return cartService.getCartByUser(userDto);
    }

    @PostMapping("/add")
    public CartDto addProductToCart(@AuthenticationPrincipal UserDto userDto, @RequestBody ProductDto productDto) {
        return cartService.addProductToCart(userDto, productDto);
    }

    @PostMapping("/remove")
    public CartDto removeProductFromCart(@AuthenticationPrincipal UserDto userDto, @RequestBody ProductDto productDto) {
        return cartService.removeProductFromCart(userDto, productDto);
    }
}