package com.example.demo.servicetest;

import com.example.demo.domain.DTO.UsuarioDTO;
import com.example.demo.domain.implementacionService.UsuarioService;
import com.example.demo.infrastructure.mapper.UsuarioMapper;

import com.example.demo.infrastructure.repository.UsuarioRepository;
import com.example.demo.domain.exception.UsuarioNoEncontradoException;

import com.example.demo.infrastructure.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setCorreo("juan@mail.com");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNombre("Juan");
        usuarioDTO.setCorreo("juan@mail.com");
    }

    @Test
    void testObtenerTodos() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        List<UsuarioDTO> result = usuarioService.obtenerTodos();

        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombre());
        verify(usuarioRepository).findAll();
    }

    @Test
    void testObtenerPorId_Existente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.obtenerPorId(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
    }

    @Test
    void testObtenerPorId_NoExistente() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        UsuarioDTO result = usuarioService.obtenerPorId(99L);

        assertNull(result);
    }

    @Test
    void testCrearUsuario() {
        when(usuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.crearUsuario(usuarioDTO);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testEliminar_UsuarioExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        usuarioService.eliminar(1L);

        verify(usuarioRepository).delete(usuario);
    }

    @Test
    void testEliminar_UsuarioNoExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.eliminar(1L));
    }
}
