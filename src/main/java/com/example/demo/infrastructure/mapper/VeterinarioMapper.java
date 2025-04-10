package com.example.demo.infrastructure.mapper;


import com.example.demo.domain.DTO.VeterinarioDTO;
import com.example.demo.infrastructure.entity.Veterinario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeterinarioMapper {
    VeterinarioDTO toDTO(Veterinario entity);
    Veterinario toEntity(VeterinarioDTO dto);
}

