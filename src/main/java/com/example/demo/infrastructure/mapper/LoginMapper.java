package com.example.demo.infrastructure.mapper;


import com.example.demo.domain.DTO.LoginDTO;
import com.example.demo.infrastructure.entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginDTO toDTO(Login login);
    Login toEntity(LoginDTO loginDTO);

}
