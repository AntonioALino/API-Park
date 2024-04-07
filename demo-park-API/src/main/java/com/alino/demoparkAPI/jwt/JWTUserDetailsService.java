package com.alino.demoparkAPI.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alino.demoparkAPI.entity.Cargo;
import com.alino.demoparkAPI.entity.Usuario;
import com.alino.demoparkAPI.service.UsuarioService;
import com.alino.demoparkAPI.web.DTO.UsuarioCreateDTO;
import com.alino.demoparkAPI.web.DTO.UsuarioJWTDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JWTUserDetailsService implements UserDetailsService {

  private UsuarioService usuarioService;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Usuario usuario = usuarioService.enctUsuario(username);
    return (UserDetails) new JWTUserDetails(new UsuarioJWTDTO(usuario.getUsuario(), usuario.getSenha(), usuario.getCargo()));
  }
  
  public JWTToken getTokenAuthenticated(String usuario){

     Cargo cargo = usuarioService.buscarCargoPorUsername(usuario);

     return JWTUtils.creaToken(usuario, cargo.name().substring("CARGO".length()));

  }


}
