package com.example.demo.infrastructure.repository;


import com.example.demo.infrastructure.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByVeterinarioId(Long veterinarioId);
    List<Cita> findByVeterinarioIdAndFechaHoraBefore(Long veterinarioId, LocalDateTime now); // historial
    List<Cita> findByVeterinarioIdAndFechaHoraAfter(Long veterinarioId, LocalDateTime now);  // futuras
}

