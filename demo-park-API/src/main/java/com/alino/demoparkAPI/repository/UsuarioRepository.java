package com.alino.demoparkAPI.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alino.demoparkAPI.entity.Cargo;
import com.alino.demoparkAPI.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

  public Optional <Usuario> findByUsername(String usuario);


  @Query("select cargo from Usuario u where u.usuario like: usuario")
  public Cargo findByUsername();
    
}
