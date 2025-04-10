package com.example.demo.domain.service;


import com.example.demo.domain.DTO.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioDTO> obtenerTodos();

    UsuarioDTO obtenerPorId(Long id);

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

    void eliminar(Long id);
}

