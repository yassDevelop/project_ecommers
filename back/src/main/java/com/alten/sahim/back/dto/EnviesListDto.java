package com.alten.sahim.back.dto;

import com.alten.sahim.back.entity.Cart;
import com.alten.sahim.back.entity.EnviesList;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnviesListDto {
    private Long id;
    private UserDto userDto;
    private List<ProductDto> listProductDto;

    public static EnviesListDto toDto(EnviesList enviesList){
        return EnviesListDto.builder()
                .id(enviesList.getId())
                .userDto(UserDto.toDTO(enviesList.getUser()))
                .listProductDto(enviesList.getProducts().stream()
                        .map(ProductDto::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static EnviesList toEntity(EnviesListDto userDto){
        EnviesList enviesList = new EnviesList();
        enviesList.setUser(UserDto.toEntity(userDto.getUserDto()));
        enviesList.setProducts(userDto.getListProductDto().stream()
                .map(ProductDto::toEntity)
                .collect(Collectors.toList()));
        return enviesList;
    }
}