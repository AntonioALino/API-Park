package com.alino.demoparkAPI.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alino.demoparkAPI.entity.Usuario;
import com.alino.demoparkAPI.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
       Usuario user =  usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity <List<Usuario>> getAll(){
       List<Usuario>  user = usuarioService.enctTD();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<Usuario> getUsuarioByID(@PathVariable UUID UUID){
        Usuario user = usuarioService.enctId(UUID);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }


    @PatchMapping("/{UUID}")
    public ResponseEntity<Usuario> changePassword(@PathVariable UUID UUID, @RequestBody Usuario usuario){
        Usuario user = usuarioService.altSenha(UUID, usuario.getSenha());
        return ResponseEntity.ok(usuario);
    }
}
