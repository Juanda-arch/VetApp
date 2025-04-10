package com.example.demo.domain.service;


import com.example.demo.domain.DTO.AdminDTO;

import java.util.List;
import java.util.Optional;

public interface IAdminService {

    List<AdminDTO> getAllAdmins();

    Optional<AdminDTO> getAdminById(Long id);

    AdminDTO createAdmin(AdminDTO adminDTO);

    void deleteAdmin(Long id);

    AdminDTO updateAdmin(Long id, AdminDTO adminDTO);
}
