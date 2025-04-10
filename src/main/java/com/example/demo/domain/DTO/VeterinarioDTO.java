package com.example.demo.domain.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarioDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String fechaNacimiento;
    private String especialidad;
    private String registroProfesional;
}

