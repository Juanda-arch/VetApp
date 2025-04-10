package com.example.demo.domain.service;


import com.example.demo.domain.DTO.VeterinarioDTO;

import java.util.List;

public interface IVeterinarioService {

    VeterinarioDTO crearVeterinario(VeterinarioDTO dto);

    List<VeterinarioDTO> obtenerVeterinarios();

    VeterinarioDTO obtenerVeterinarioPorId(Long id);

    void eliminarVeterinario(Long id);

    VeterinarioDTO actualizarVeterinario(Long id, VeterinarioDTO dto);
}

