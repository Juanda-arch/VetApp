package com.example.demo.infrastructure.mapper;

import com.example.demo.domain.DTO.CitaDTO;
import com.example.demo.infrastructure.entity.Cita;
import com.example.demo.infrastructure.entity.Mascota;
import com.example.demo.infrastructure.entity.Veterinario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    CitaDTO toDTO(Cita cita);

    List<CitaDTO> toDtoList(List<Cita> citas);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mascota", source = "mascota")
    @Mapping(target = "veterinario", source = "veterinario")
    Cita toEntity(CitaDTO dto, Mascota mascota, Veterinario veterinario);
}
