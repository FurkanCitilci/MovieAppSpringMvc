package com.furkancitilci.dto.response;


import com.furkancitilci.repository.entity.EUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterResponseDto {
    private Long id;
    private String name;
    private String surName;
    private String email;
    private EUserType userType;
}
