package com.alino.demoparkAPI.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alino.demoparkAPI.entity.Cargo;
import com.alino.demoparkAPI.entity.Usuario;
import com.alino.demoparkAPI.repository.UsuarioRepository;
import com.alino.demoparkAPI.web.exception.EntityNotFoundException;
import com.alino.demoparkAPI.web.exception.PasswordInvalidException;
import com.alino.demoparkAPI.web.exception.UsernameUniqueViolationException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @org.springframework.transaction.annotation.Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Usuário {%s} já cadastrado.", usuario.getUsuario(), null));
        }

    }

    @Transactional(readOnly = true)
    public List<Usuario> enctTD() {
        return usuarioRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Usuario enctId(UUID uuid) {
        return usuarioRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException(String.format("Usuário  de ID %s não encontrado", uuid)));
    }

    @Transactional
    public Usuario altSenha(UUID uuid, String senhaAtual, String senhaNova, String confirmarSenha) {

        if (!senhaNova.equals(confirmarSenha)) {
            throw new PasswordInvalidException("A nova senha não confere com a confirmação de senha.");
        }

        Usuario user = enctId(uuid);

        if (!user.getSenha().equals(senhaAtual)) {
            throw new PasswordInvalidException("Senha incorreta.");
        }

        user.setSenha(senhaNova);
        return user;
    }

    @Transactional(readOnly = true)
    public Usuario enctUsuario(String usuario) {
        return usuarioRepository.findByUsername(usuario).orElseThrow(() -> new EntityNotFoundException(String.format("Usuario não encontrado %s", usuario)));

    }

    @Transactional(readOnly = true)
    public Cargo buscarCargoPorUsername(String usuario) {

        return usuarioRepository.findByUsername();

    }

}
