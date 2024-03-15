package com.alino.demoparkAPI.web.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioSenhaDTO {
  
  @NotBlank
  @Size(min = 6, max = 6)
  private String senhaAtual;
  
  @NotBlank
  @Size(min = 6, max = 6)
  private String senhaNova;

  @NotBlank
  @Size(min = 6, max = 6)
  private String confirmarSenha;

}
