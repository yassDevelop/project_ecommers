package com.alten.sahim.back.dto;

import com.alten.sahim.back.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Détail pour un utilisateur")
public class UserDto{
    @Schema(description = "Nom de l'utilisateur", example = "Yassine")
    private String username;

    @Schema(description = "Prenom de l'utilisateur", example = "Sahim")
    private String firstname;

    @Schema(description = "Email de l'utilisateur", example = "yass@gmail.com")
    private String email;

    @Schema(description = "Mot de passe de l'utilisateur", example = "pass@")
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