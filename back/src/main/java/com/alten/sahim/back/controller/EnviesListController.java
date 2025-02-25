package com.alten.sahim.back.controller;

import com.alten.sahim.back.dto.EnviesListDto;
import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.dto.UserDto;
import com.alten.sahim.back.entity.Cart;
import com.alten.sahim.back.entity.EnviesList;
import com.alten.sahim.back.entity.Product;
import com.alten.sahim.back.entity.User;
import com.alten.sahim.back.service.EnviesListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/EnviesList")
public class EnviesListController {
    @Autowired
    private EnviesListService enviesListService;

    @GetMapping
    public EnviesListDto getEnviesList(@AuthenticationPrincipal UserDto userDto) {
        return enviesListService.getEnviesListByUser(userDto);
    }

    @PostMapping("/add")
    public EnviesListDto addProductToEnviesList(@AuthenticationPrincipal UserDto userDto, @RequestBody ProductDto productDto) {
        return enviesListService.addProductToEnviesList(userDto, productDto);
    }

    @PostMapping("/remove")
    public EnviesListDto removeProductFromEnviesList(@AuthenticationPrincipal UserDto userDto, @RequestBody ProductDto productDto) {
        return enviesListService.removeProductFromEnviesList(userDto, productDto);
    }
}