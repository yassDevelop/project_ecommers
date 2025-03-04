package com.alten.sahim.back.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    private String token;

    public static ResponseDto toDto(String token){
           return ResponseDto.builder()
                        .token(token)
                        .build();
    }
}
