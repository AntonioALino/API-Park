package com.alino.demoparkAPI.web.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioSenhaDTO {
  
  private String senhaAtual;
  private String senhaNova;
  private String confirmarSenha;

}
