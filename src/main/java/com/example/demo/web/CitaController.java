package com.example.demo.web;

import com.example.demo.domain.DTO.CitaDTO;
import com.example.demo.domain.implementacionService.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @PostMapping
    public CitaDTO crearCita(@RequestBody CitaDTO citaDTO) {
        return citaService.crearCita(citaDTO);
    }

    @GetMapping("/veterinario/{id}")
    public List<CitaDTO> getCitasPorVeterinario(@PathVariable Long id) {
        return citaService.obtenerCitasPorVeterinario(id);
    }

    @GetMapping("/veterinario/{id}/historial")
    public List<CitaDTO> getHistorialConsultas(@PathVariable Long id) {
        return citaService.obtenerHistorialConsultas(id);
    }

    @GetMapping("/veterinario/{id}/pendientes")
    public List<CitaDTO> getCitasPendientes(@PathVariable Long id) {
        return citaService.obtenerCitasPendientes(id);
    }
}
