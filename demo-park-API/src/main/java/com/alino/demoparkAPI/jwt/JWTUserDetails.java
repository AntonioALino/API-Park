package com.alino.demoparkAPI.jwt;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.authority.AuthorityUtils;
import com.alino.demoparkAPI.entity.Usuario;

import com.alino.demoparkAPI.web.DTO.UsuarioJWTDTO;
import com.alino.demoparkAPI.web.DTO.mapper.BeanMapper;
import com.alino.demoparkAPI.web.DTO.mapper.UsuarioMapper;;

public class JWTUserDetails extends User {

  private Usuario usuario;

  @Autowired
  private UsuarioJWTDTO usarioJWTDTO;
  

  public JWTUserDetails(UsuarioJWTDTO usuarioJWTDTO){
    UsuarioJWTDTO usuario = new UsuarioJWTDTO(usarioJWTDTO.getUsuario(), usarioJWTDTO.getSenha(), AuthorityUtils.createAuthorityList(getCargo()));
    this.usuario = UsuarioMapper.toJWTDTO(usuarioJWTDTO);
  }

  public UUID getId(){
    return this.usuario.getUUID();
  }

  public String getCargo(){
    return this.usuario.getCargo().name();
  }


}
