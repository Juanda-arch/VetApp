package com.example.demo.domain.service;

import com.example.demo.domain.DTO.FacturaDTO;

import java.util.List;

public interface IFacturaService {

    FacturaDTO crearFactura(FacturaDTO facturaDTO);

    List<FacturaDTO> obtenerTodas();

    FacturaDTO obtenerPorId(Long id);

    void eliminar(Long id);
}

