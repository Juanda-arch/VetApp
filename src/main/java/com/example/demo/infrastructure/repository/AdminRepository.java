package com.example.demo.infrastructure.repository;


import com.example.demo.infrastructure.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}

