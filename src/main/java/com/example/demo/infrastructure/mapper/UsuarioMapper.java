package com.example.demo.infrastructure.mapper;

import com.example.demo.domain.DTO.UsuarioDTO;
import com.example.demo.infrastructure.entity.Usuario;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);

}

