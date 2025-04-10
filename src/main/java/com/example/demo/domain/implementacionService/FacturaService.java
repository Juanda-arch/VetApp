package com.example.demo.domain.implementacionService;


import com.example.demo.domain.DTO.FacturaDTO;
import com.example.demo.infrastructure.mapper.FacturaMapper;
import com.example.demo.domain.service.IFacturaService;
import com.example.demo.infrastructure.entity.Admin;
import com.example.demo.infrastructure.entity.Factura;
import com.example.demo.infrastructure.repository.AdminRepository;
import com.example.demo.infrastructure.repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturaService implements IFacturaService {

    private final FacturaRepository facturaRepository;
    private final AdminRepository adminRepository;
    private final FacturaMapper facturaMapper;

    public FacturaDTO crearFactura(FacturaDTO facturaDTO) {
        Factura factura = facturaMapper.toEntity(facturaDTO);
        Optional<Admin> admin = adminRepository.findById(facturaDTO.getAdminId());
        admin.ifPresent(factura::setAdmin);
        return facturaMapper.toDTO(facturaRepository.save(factura));
    }

    public List<FacturaDTO> obtenerTodas() {
        return facturaRepository.findAll().stream()
                .map(facturaMapper::toDTO)
                .toList();
    }

    public FacturaDTO obtenerPorId(Long id) {
        return facturaRepository.findById(id)
                .map(facturaMapper::toDTO)
                .orElse(null);
    }

    public void eliminar(Long id) {
        facturaRepository.deleteById(id);
    }
}

