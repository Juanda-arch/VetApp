package com.example.demo.domain.service;

import com.example.demo.domain.DTO.MascotaDTO;

import java.util.List;
import java.util.Optional;

public interface IMascotaService {
    List<MascotaDTO> findAll();
    Optional<MascotaDTO> findById(Long id);
    List<MascotaDTO> findByUsuarioId(Long usuarioId);
    MascotaDTO save(MascotaDTO mascotaDTO);
    void deleteById(Long id);
    MascotaDTO update(MascotaDTO mascotaDTO);
}