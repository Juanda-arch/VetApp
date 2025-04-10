package com.example.demo.web;

import com.example.demo.domain.DTO.MascotaDTO;
import com.example.demo.domain.service.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final IMascotaService mascotaService;

    @Autowired
    public MascotaController(IMascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> findAll() {
        List<MascotaDTO> mascotas = mascotaService.findAll();
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> findById(@PathVariable Long id) {
        return mascotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MascotaDTO>> findByUsuarioId(@PathVariable Long usuarioId) {
        List<MascotaDTO> mascotas = mascotaService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(mascotas);
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> save(@RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO savedMascota = mascotaService.save(mascotaDTO);
        return new ResponseEntity<>(savedMascota, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> update(@PathVariable Long id, @RequestBody MascotaDTO mascotaDTO) {
        mascotaDTO.setId(id);
        try {
            MascotaDTO updatedMascota = mascotaService.update(mascotaDTO);
            return ResponseEntity.ok(updatedMascota);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            mascotaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}