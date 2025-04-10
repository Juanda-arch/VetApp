package com.example.demo.infrastructure.mapper;

import com.example.demo.domain.DTO.FacturaDTO;
import com.example.demo.infrastructure.entity.Factura;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    @Mapping(source = "admin.id", target = "adminId")
    FacturaDTO toDTO(Factura factura);

    @Mapping(source = "adminId", target = "admin.id")
    Factura toEntity(FacturaDTO facturaDTO);
}
