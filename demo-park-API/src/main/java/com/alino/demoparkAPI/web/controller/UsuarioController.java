package com.alino.demoparkAPI.web.controller;

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
import com.alino.demoparkAPI.web.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura do usuário.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @Operation(summary = "Criar novo usuário", description = "Recurso para criar novo usuário.", responses = {
        @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class ))), 
        @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no site.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
        @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválida.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioCreateDTO createDTO){
       Usuario user =  usuarioService.salvar(UsuarioMapper.toUsuario(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDTO(user));
    }
    

    @Operation(summary = "Listar todos os usuários", description = "Retorna todos os usuários, sem utilizar nenhum filtro.", responses = {
        @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso.", content = @Content(mediaType = "application/json", array = @ArraySchema (schema = @Schema(implementation = UsuarioResponseDTO.class))))
    })
    @GetMapping
    public ResponseEntity <List<UsuarioResponseDTO>> getAll(){
       List<Usuario>  user = usuarioService.enctTD();
        return ResponseEntity.ok(UsuarioMapper.toListDTO(user));
    }

    @Operation(summary = "Recuperar usuário por ID", description = "Recurso para ecnontrar um usuário por ID.", responses = {
        @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class ))), 
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/{UUID}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioByID(@PathVariable UUID UUID){
        Usuario user = usuarioService.enctId(UUID);
        return ResponseEntity.status(HttpStatus.FOUND).body(UsuarioMapper.toDTO(user));
    }

    @Operation(summary = "Atualizar senha.", description = "Recurso para alterar a senha.", responses = {
        @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class ))), 
        @ApiResponse(responseCode = "400", description = "Senha não confere.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PatchMapping("/{UUID}")
    public ResponseEntity<Void> changePassword(@PathVariable UUID UUID, @Valid @RequestBody UsuarioSenhaDTO dto){
        Usuario user = usuarioService.altSenha(UUID, dto.getSenhaAtual(), dto.getSenhaNova(), dto.getConfirmarSenha());
        return ResponseEntity.noContent().build();
    }
}
