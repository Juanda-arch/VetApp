package com.example.demo.domain.implementacionService;

import com.example.demo.domain.DTO.UsuarioDTO;
import com.example.demo.infrastructure.mapper.UsuarioMapper;
import com.example.demo.domain.exception.UsuarioNoEncontradoException;
import com.example.demo.domain.service.IUsuarioService;
import com.example.demo.infrastructure.entity.Usuario;
import com.example.demo.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> obtenerTodos() {
            return usuarioRepository.findAll().stream()
                    .map(usuarioMapper::toDTO)
                    .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElse(null);
    }

    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado"));
        usuarioRepository.delete(usuario);
    }

}
