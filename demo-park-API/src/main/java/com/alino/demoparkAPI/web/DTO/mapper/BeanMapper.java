package com.alino.demoparkAPI.web.DTO.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanMapper {

    @Bean
    static ModelMapper mapper(){
    return new ModelMapper();
}   


}
