package com.example.demo.domain.service;


import com.example.demo.domain.DTO.CitaDTO;

import java.util.List;

public interface ICitaService {

    CitaDTO crearCita(CitaDTO dto);

    List<CitaDTO> obtenerCitasPorVeterinario(Long veterinarioId);

    List<CitaDTO> obtenerHistorialConsultas(Long veterinarioId);

    List<CitaDTO> obtenerCitasPendientes(Long veterinarioId);
}

