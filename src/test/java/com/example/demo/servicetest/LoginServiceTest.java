package com.example.demo.servicetest;

import com.example.demo.domain.DTO.LoginDTO;
import com.example.demo.domain.implementacionService.LoginService;
import com.example.demo.infrastructure.mapper.LoginMapper;
import com.example.demo.infrastructure.repository.LoginRepository;
import com.example.demo.infrastructure.entity.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginRepository loginRepository;
    private LoginMapper loginMapper;
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginRepository = mock(LoginRepository.class);
        loginMapper = mock(LoginMapper.class);
        loginService = new LoginService(loginRepository, loginMapper);
    }

    @Test
    void testCrearUsuario() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsuario("testUser");
        loginDTO.setContrasena("1234");

        Login loginEntity = new Login();
        loginEntity.setUsuario("testUser");
        loginEntity.setContrasena("1234");

        when(loginMapper.toEntity(loginDTO)).thenReturn(loginEntity);
        when(loginRepository.save(loginEntity)).thenReturn(loginEntity);
        when(loginMapper.toDTO(loginEntity)).thenReturn(loginDTO);

        // Act
        LoginDTO result = loginService.crearUsuario(loginDTO);

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getUsuario());
        verify(loginRepository, times(1)).save(loginEntity);
    }

    @Test
    void testVerificarLogin_RetornaNull() {
        // Act
        LoginDTO result = loginService.verificarLogin("usuario", "1234");

        // Assert
        assertNull(result);
    }
}
