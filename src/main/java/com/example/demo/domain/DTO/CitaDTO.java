package com.example.demo.domain.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Long mascotaId;
    private Long veterinarioId;
    private boolean atendida;
}
