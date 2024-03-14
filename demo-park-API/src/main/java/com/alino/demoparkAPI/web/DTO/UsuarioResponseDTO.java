package com.alino.demoparkAPI.web.DTO;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioResponseDTO {
    
    private UUID UUID;
    private String usuario;
    private String cargo;

}
