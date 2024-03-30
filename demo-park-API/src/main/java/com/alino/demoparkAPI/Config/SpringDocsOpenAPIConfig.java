package com.alino.demoparkAPI.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocsOpenAPIConfig {
     
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
        .title("Rest API - Spring Park")
        .description("API para realizar a gestão de estacionamento de veículos.")
        .version("v1")
        .contact(new Contact().name("Antônio Lino")));
    }
}
