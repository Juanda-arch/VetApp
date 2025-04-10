package com.example.demo.web;

import com.example.demo.domain.DTO.VeterinarioDTO;
import com.example.demo.domain.implementacionService.VeterinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @PostMapping
    public VeterinarioDTO crearVeterinario(@RequestBody VeterinarioDTO dto) {
        return veterinarioService.crearVeterinario(dto);
    }

    @GetMapping
    public List<VeterinarioDTO> obtenerVeterinarios() {
        return veterinarioService.obtenerVeterinarios();
    }

    @GetMapping("/{id}")
    public VeterinarioDTO obtenerVeterinarioPorId(@PathVariable Long id) {
        return veterinarioService.obtenerVeterinarioPorId(id);
    }

    @PutMapping("/{id}")
    public VeterinarioDTO actualizarVeterinario(@PathVariable Long id, @RequestBody VeterinarioDTO dto) {
        return veterinarioService.actualizarVeterinario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarVeterinario(@PathVariable Long id) {
        veterinarioService.eliminarVeterinario(id);
    }
}
