package com.example.demo.infrastructure.mapper;


import com.example.demo.domain.DTO.AdminDTO;
import com.example.demo.infrastructure.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminDTO toDTO(Admin admin);
    Admin toEntity(AdminDTO adminDTO);
}
