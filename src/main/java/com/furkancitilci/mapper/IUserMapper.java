package com.furkancitilci.mapper;


import com.furkancitilci.dto.request.UserRegisterRequestDto;
import com.furkancitilci.dto.response.LoginResponseDto;
import com.furkancitilci.dto.response.UserRegisterResponseDto;
import com.furkancitilci.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {


    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);


    User toUser(final UserRegisterRequestDto dto);

    UserRegisterResponseDto toUserRegisterResponseDto(final User user);

    LoginResponseDto toLoginRespnseDto(final User user);


}