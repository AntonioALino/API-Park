package com.alino.demoparkAPI.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alino.demoparkAPI.entity.Usuario;
import com.alino.demoparkAPI.service.UsuarioService;
import com.alino.demoparkAPI.web.DTO.UsuarioCreateDTO;
import com.alino.demoparkAPI.web.DTO.UsuarioResponseDTO;
import com.alino.demoparkAPI.web.DTO.UsuarioSenhaDTO;
import com.alino.demoparkAPI.web.DTO.mapper.UsuarioMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@RequestBody UsuarioCreateDTO createDTO){
       Usuario user =  usuarioService.salvar(UsuarioMapper.toUsuario(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDTO(user));
    }

    @GetMapping
    public ResponseEntity <List<UsuarioResponseDTO>> getAll(){
       List<Usuario>  user = usuarioService.enctTD();
        return ResponseEntity.ok(UsuarioMapper.toListDTO(user));
    }

    @GetMapping("/{UUID}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioByID(@PathVariable UUID UUID){
        Usuario user = usuarioService.enctId(UUID);
        return ResponseEntity.status(HttpStatus.FOUND).body(UsuarioMapper.toDTO(user));
    }


    @PatchMapping("/{UUID}")
    public ResponseEntity<Void> changePassword(@PathVariable UUID UUID, @RequestBody UsuarioSenhaDTO dto){
        Usuario user = usuarioService.altSenha(UUID, dto.getSenhaAtual(), dto.getSenhaNova(), dto.getConfirmarSenha());
        return ResponseEntity.noContent().build();
    }
}
