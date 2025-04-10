package com.example.demo.domain.implementacionService;

import com.example.demo.domain.DTO.LoginDTO;
import com.example.demo.infrastructure.mapper.LoginMapper;
import com.example.demo.infrastructure.repository.LoginRepository;
import com.example.demo.infrastructure.entity.Login;
import com.example.demo.domain.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService implements ILoginService {


    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;


    @Override
    public LoginDTO buscarLogin(String u, String c) {
        Login login = loginRepository.buscarLogin(u, c).orElse(null);
        return loginMapper.toDTO(login);
    }

    @Override
    public LoginDTO crearUsuario(LoginDTO loginDTO) {
        Login login = loginMapper.toEntity(loginDTO);
        login = loginRepository.save(login);
        return loginMapper.toDTO(login);
    }

    @Override
    public LoginDTO verificarLogin(String usuario, String contrasena) {
        return null;
    }


}
