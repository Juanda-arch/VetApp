package com.example.demo.domain.DTO;


import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {
    private Long id;
    private LocalDate fecha;
    private Double total;
    private String descripcion;
    private Long adminId;
}
