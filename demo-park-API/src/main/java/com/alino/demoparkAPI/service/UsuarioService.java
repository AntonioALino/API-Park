package com.alino.demoparkAPI.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alino.demoparkAPI.entity.Usuario;
import com.alino.demoparkAPI.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @org.springframework.transaction.annotation.Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> enctTD(){
        return usuarioRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Usuario enctId(UUID uuid) {
        return usuarioRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public Usuario altSenha(UUID uuid, String senhaAtual, String senhaNova, String confirmarSenha) {

        if (!senhaNova.equals(confirmarSenha)) {
            throw new RuntimeException("A nova senha não confere com a confirmação de senha.");
        }

        Usuario user = enctId(uuid);

        if (!user.getSenha().equals(senhaAtual)) {
            throw new RuntimeException("Senha incorreta.");
        }

        user.setSenha(senhaNova);
        return user;
    }

}
