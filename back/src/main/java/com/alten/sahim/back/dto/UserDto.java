package com.alten.sahim.back.dto;

import com.alten.sahim.back.entity.Product;
import com.alten.sahim.back.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto{
    private String username;
    private String firstname;
    private String email;
    private String password;

    public static UserDto toDTO(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }

    public static User toEntity(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        return user;
    }
}