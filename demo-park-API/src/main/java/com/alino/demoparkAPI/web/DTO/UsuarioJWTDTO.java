package com.alino.demoparkAPI.web.DTO;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.alino.demoparkAPI.entity.Cargo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioJWTDTO {

  
  private String usuario;
  private String senha;
  private Cargo cargo;

  public UsuarioJWTDTO(String usuario, String senha, List<GrantedAuthority> list ){
    
  }

  public UsuarioJWTDTO(String usuario, String senha, Cargo cargo){
    
  }

}
