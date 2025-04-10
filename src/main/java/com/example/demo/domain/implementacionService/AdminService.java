package com.example.demo.domain.implementacionService;


import com.example.demo.domain.DTO.AdminDTO;
import com.example.demo.infrastructure.mapper.AdminMapper;
import com.example.demo.domain.service.IAdminService;
import com.example.demo.infrastructure.entity.Admin;

import com.example.demo.infrastructure.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper = AdminMapper.INSTANCE;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AdminDTO> getAdminById(Long id) {
        return adminRepository.findById(id)
                .map(adminMapper::toDTO);
    }

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Admin admin = adminMapper.toEntity(adminDTO);
        Admin saved = adminRepository.save(admin);
        return adminMapper.toDTO(saved);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
        Admin admin = adminRepository.findById(id).orElseThrow();
        admin.setNombre(adminDTO.getNombre());
        admin.setCorreo(adminDTO.getCorreo());
        admin.setTelefono(adminDTO.getTelefono());
        admin.setFechaNacimiento(adminDTO.getFechaNacimiento());
        return adminMapper.toDTO(adminRepository.save(admin));
    }
}

