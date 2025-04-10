package com.example.demo.domain.implementacionService;

import com.example.demo.domain.DTO.MascotaDTO;
import com.example.demo.infrastructure.repository.MascotaRepository;
import com.example.demo.infrastructure.repository.UsuarioRepository;
import com.example.demo.domain.service.IMascotaService;
import com.example.demo.infrastructure.entity.Mascota;
import com.example.demo.infrastructure.mapper.MascotaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService implements IMascotaService {

    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MascotaMapper mascotaMapper;

    @Autowired
    public MascotaService(MascotaRepository mascotaRepository,
                          UsuarioRepository usuarioRepository,
                          MascotaMapper mascotaMapper) {
        this.mascotaRepository = mascotaRepository;
        this.usuarioRepository = usuarioRepository;
        this.mascotaMapper = mascotaMapper;
    }

    @Override
    public List<MascotaDTO> findAll() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        return mascotaMapper.toDTOList(mascotas);
    }

    @Override
    public Optional<MascotaDTO> findById(Long id) {
        return mascotaRepository.findById(id)
                .map(mascotaMapper::toDTO);
    }

    @Override
    public List<MascotaDTO> findByUsuarioId(Long usuarioId) {
        List<Mascota> mascotas = mascotaRepository.findByUsuarioId(usuarioId);
        return mascotaMapper.toDTOList(mascotas);
    }

    @Override
    public MascotaDTO save(MascotaDTO mascotaDTO) {
        // Verificar que el usuario existe
        if (!usuarioRepository.existsById(mascotaDTO.getUsuarioId())) {
            throw new RuntimeException("Usuario no encontrado con ID: " + mascotaDTO.getUsuarioId());
        }

        Mascota mascota = mascotaMapper.toEntity(mascotaDTO);
        Mascota savedMascota = mascotaRepository.save(mascota);

        return mascotaMapper.toDTO(savedMascota);
    }

    @Override
    public void deleteById(Long id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Mascota no encontrada con ID: " + id);
        }
    }

    @Override
    public MascotaDTO update(MascotaDTO mascotaDTO) {
        if (mascotaDTO.getId() == null || !mascotaRepository.existsById(mascotaDTO.getId())) {
            throw new RuntimeException("No se puede actualizar. Mascota no encontrada con ID: " + mascotaDTO.getId());
        }

        // Verificar que el usuario existe
        if (!usuarioRepository.existsById(mascotaDTO.getUsuarioId())) {
            throw new RuntimeException("Usuario no encontrado con ID: " + mascotaDTO.getUsuarioId());
        }

        Mascota mascota = mascotaMapper.toEntity(mascotaDTO);
        Mascota updatedMascota = mascotaRepository.save(mascota);

        return mascotaMapper.toDTO(updatedMascota);
    }
}