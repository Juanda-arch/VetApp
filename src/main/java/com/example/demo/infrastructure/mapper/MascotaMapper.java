package com.example.demo.infrastructure.mapper;

import com.example.demo.domain.DTO.MascotaDTO;
import com.example.demo.infrastructure.entity.Mascota;
import com.example.demo.infrastructure.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    MascotaDTO toDTO(Mascota mascota);

    @Mapping(source = "usuarioId", target = "usuario", qualifiedByName = "usuarioIdToUsuario")
    Mascota toEntity(MascotaDTO dto);

    List<MascotaDTO> toDTOList(List<Mascota> mascotas);

    @Named("usuarioIdToUsuario")
    default Usuario usuarioIdToUsuario(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        return usuario;
    }

    @Mapping(source = "usuarioId", target = "usuario", qualifiedByName = "usuarioIdToUsuario")
    void updateEntityFromDTO(MascotaDTO dto, @MappingTarget Mascota mascota);
}