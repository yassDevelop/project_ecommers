package com.alten.sahim.back.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private String email;
    private String password;
}
