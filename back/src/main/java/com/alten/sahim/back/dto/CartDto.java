package com.alten.sahim.back.dto;

import com.alten.sahim.back.entity.Cart;
import com.alten.sahim.back.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private Long id;
    private UserDto userDto;
    private List<ProductDto> listProductDto;

    public static CartDto toDTO(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .userDto(UserDto.toDTO(cart.getUser()))
                .listProductDto(cart.getProducts().stream()
                        .map(ProductDto::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Cart toEntity(CartDto userDto){
        Cart cart = new Cart();
        cart.setUser(UserDto.toEntity(userDto.getUserDto()));
        cart.setProducts(userDto.getListProductDto().stream()
                .map(ProductDto::toEntity)
                .collect(Collectors.toList()));
        return cart;
    }
}