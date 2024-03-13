package com.alino.demoparkAPI.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.alino.demoparkAPI.entity.Usuario;
import com.alino.demoparkAPI.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    @org.springframework.transaction.annotation.Transactional
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Usuario enctId(UUID uuid){
        return usuarioRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
