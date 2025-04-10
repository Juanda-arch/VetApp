package com.example.demo.infrastructure.repository;


import com.example.demo.infrastructure.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
