package com.example.demo.servicetest;

import com.example.demo.domain.DTO.CitaDTO;
import com.example.demo.domain.implementacionService.CitaService;
import com.example.demo.infrastructure.mapper.CitaMapper;
import com.example.demo.infrastructure.repository.CitaRepository;
import com.example.demo.infrastructure.repository.MascotaRepository;
import com.example.demo.infrastructure.repository.VeterinarioRepository;
import com.example.demo.infrastructure.entity.Cita;
import com.example.demo.infrastructure.entity.Mascota;
import com.example.demo.infrastructure.entity.Veterinario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @Mock
    private CitaMapper citaMapper;

    @InjectMocks
    private CitaService citaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearCita_deberiaGuardarYRetornarDTO() {
        // Arrange
        CitaDTO inputDTO = new CitaDTO();
        inputDTO.setMascotaId(1L);
        inputDTO.setVeterinarioId(2L);

        Mascota mascota = new Mascota();
        Veterinario veterinario = new Veterinario();
        Cita cita = new Cita();
        Cita citaGuardada = new Cita();
        CitaDTO salidaDTO = new CitaDTO();

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));
        when(veterinarioRepository.findById(2L)).thenReturn(Optional.of(veterinario));
        when(citaMapper.toEntity(inputDTO, mascota, veterinario)).thenReturn(cita);
        when(citaRepository.save(cita)).thenReturn(citaGuardada);
        when(citaMapper.toDTO(citaGuardada)).thenReturn(salidaDTO);

        // Act
        CitaDTO resultado = citaService.crearCita(inputDTO);

        // Assert
        assertNotNull(resultado);
        verify(citaRepository).save(cita);
    }

    @Test
    void obtenerCitasPorVeterinario_deberiaRetornarListaDTOs() {
        // Arrange
        Long vetId = 1L;
        Cita cita1 = new Cita();
        Cita cita2 = new Cita();
        List<Cita> citas = Arrays.asList(cita1, cita2);
        CitaDTO dto1 = new CitaDTO();
        CitaDTO dto2 = new CitaDTO();

        when(citaRepository.findByVeterinarioId(vetId)).thenReturn(citas);
        when(citaMapper.toDTO(cita1)).thenReturn(dto1);
        when(citaMapper.toDTO(cita2)).thenReturn(dto2);

        // Act
        List<CitaDTO> resultado = citaService.obtenerCitasPorVeterinario(vetId);

        // Assert
        assertEquals(2, resultado.size());
        verify(citaRepository).findByVeterinarioId(vetId);
    }

    @Test
    void obtenerHistorialConsultas_deberiaRetornarCitasPasadas() {
        // Arrange
        Long vetId = 1L;
        Cita cita = new Cita();
        CitaDTO dto = new CitaDTO();
        LocalDateTime ahora = LocalDateTime.now();

        when(citaRepository.findByVeterinarioIdAndFechaHoraBefore(eq(vetId), any())).thenReturn(List.of(cita));
        when(citaMapper.toDTO(cita)).thenReturn(dto);

        // Act
        List<CitaDTO> resultado = citaService.obtenerHistorialConsultas(vetId);

        // Assert
        assertEquals(1, resultado.size());
        verify(citaRepository).findByVeterinarioIdAndFechaHoraBefore(eq(vetId), any());
    }

    @Test
    void obtenerCitasPendientes_deberiaRetornarCitasFuturas() {
        // Arrange
        Long vetId = 1L;
        Cita cita = new Cita();
        CitaDTO dto = new CitaDTO();

        when(citaRepository.findByVeterinarioIdAndFechaHoraAfter(eq(vetId), any())).thenReturn(List.of(cita));
        when(citaMapper.toDTO(cita)).thenReturn(dto);

        // Act
        List<CitaDTO> resultado = citaService.obtenerCitasPendientes(vetId);

        // Assert
        assertEquals(1, resultado.size());
        verify(citaRepository).findByVeterinarioIdAndFechaHoraAfter(eq(vetId), any());
    }
}
