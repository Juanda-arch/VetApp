package com.example.demo.domain.service;


import com.example.demo.domain.DTO.LoginDTO;
import org.springframework.stereotype.Service;

@Service
public interface ILoginService {

    LoginDTO buscarLogin(String u, String c);
    LoginDTO crearUsuario(LoginDTO loginDTO);
    LoginDTO verificarLogin(String usuario, String contrasena);
}
