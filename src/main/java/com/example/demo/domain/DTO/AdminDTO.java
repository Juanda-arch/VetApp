package com.example.demo.domain.DTO;


import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String fechaNacimiento;
}
