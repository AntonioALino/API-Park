package com.alino.demoparkAPI.web.DTO.mapper;

import java.util.Optional;

import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alino.demoparkAPI.entity.Usuario;
import com.alino.demoparkAPI.web.DTO.UsuarioCreateDTO;
import com.alino.demoparkAPI.web.DTO.UsuarioResponseDTO;


@Service
public class UsuarioMapper {

    @Autowired
    public static Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO){
        new BeanMapper();
        return BeanMapper.mapper().map(usuarioCreateDTO, Usuario.class);
    }

    @Autowired
    public static UsuarioResponseDTO toDTO(Usuario usuario){
        String cargo = usuario.getCargo().name();
        PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<Usuario,UsuarioResponseDTO>() {
            @Override
            protected void configure(){
                map().setCargo(cargo);
            };
            
        };
        TypeMap<Usuario, UsuarioResponseDTO> typeMap = BeanMapper.mapper().createTypeMap(Usuario.class, UsuarioResponseDTO.class);
        typeMap.addMappings(props);
        new BeanMapper();
        return BeanMapper.mapper().map(usuario, UsuarioResponseDTO.class);
    }
}
