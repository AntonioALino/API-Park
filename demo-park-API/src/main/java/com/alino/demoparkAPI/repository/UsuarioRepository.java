package com.alino.demoparkAPI.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alino.demoparkAPI.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    
}
