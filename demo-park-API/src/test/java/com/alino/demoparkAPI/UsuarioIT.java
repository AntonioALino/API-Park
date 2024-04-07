package com.alino.demoparkAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.alino.demoparkAPI.web.DTO.UsuarioCreateDTO;
import com.alino.demoparkAPI.web.DTO.UsuarioResponseDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UsuarioIT {
  

  @Autowired
  WebTestClient testClient;

  
  @Test
  public void createUsuario_ConUsernamePasswordValid_ReturnCreateUsuario201(){
   UsuarioResponseDTO responseBody =  
    testClient
    .post()
    .uri("/api/v1/usuarios")
    .contentType(MediaType.APPLICATION_JSON)
    .bodyValue(new UsuarioCreateDTO("antonioLino@email.com", "123456"))
    .exchange()
    .expectStatus().isCreated()
    .expectBody(UsuarioResponseDTO.class)
    .returnResult()
    .getResponseBody();

    org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getUUID()).isNotNull();
    org.assertj.core.api.Assertions.assertThat(responseBody.getUsuario()).isEqualTo("antonio@email.com");
    org.assertj.core.api.Assertions.assertThat(responseBody.getCargo()).isEqualTo("ADMIN");
  }


}
