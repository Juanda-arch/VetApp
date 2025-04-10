package com.example.demo.infrastructure.repository;


import com.example.demo.infrastructure.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query(value = "SELECT * FROM login WHERE usuario = ?1 AND contrasena = ?2", nativeQuery = true)
    Optional<Login> buscarLogin(String user, String contrasena);
}
