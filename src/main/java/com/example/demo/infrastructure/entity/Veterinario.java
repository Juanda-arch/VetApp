package com.example.demo.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario extends Usuario {

    private String especialidad;
    private String registroProfesional;
}

