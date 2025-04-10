package com.example.demo.domain.implementacionService;

import com.example.demo.domain.DTO.CitaDTO;
import com.example.demo.infrastructure.mapper.CitaMapper;
import com.example.demo.domain.service.ICitaService;
import com.example.demo.infrastructure.entity.Cita;
import com.example.demo.infrastructure.entity.Mascota;
import com.example.demo.infrastructure.entity.Veterinario;
import com.example.demo.infrastructure.repository.CitaRepository;
import com.example.demo.infrastructure.repository.MascotaRepository;
import com.example.demo.infrastructure.repository.VeterinarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaService implements ICitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final CitaMapper citaMapper;

    public CitaDTO crearCita(CitaDTO dto) {
        Mascota mascota = mascotaRepository.findById(dto.getMascotaId()).orElseThrow();
        Veterinario veterinario = veterinarioRepository.findById(dto.getVeterinarioId()).orElseThrow();
        Cita cita = citaMapper.toEntity(dto, mascota, veterinario);
        return citaMapper.toDTO(citaRepository.save(cita));
    }

    public List<CitaDTO> obtenerCitasPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioId(veterinarioId).stream()
                .map(citaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CitaDTO> obtenerHistorialConsultas(Long veterinarioId) {
        return citaRepository.findByVeterinarioIdAndFechaHoraBefore(veterinarioId, LocalDateTime.now())
                .stream().map(citaMapper::toDTO).collect(Collectors.toList());
    }

    public List<CitaDTO> obtenerCitasPendientes(Long veterinarioId) {
        return citaRepository.findByVeterinarioIdAndFechaHoraAfter(veterinarioId, LocalDateTime.now())
                .stream().map(citaMapper::toDTO).collect(Collectors.toList());
    }
}
