package com.alino.demoparkAPI.web.DTO.mapper;


import java.util.List;
import java.util.stream.Collectors;

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
    public static Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        new BeanMapper();
        return BeanMapper.mapper().map(usuarioCreateDTO, Usuario.class);
    }

    @Autowired
    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        String cargo = usuario.getCargo().name();
        PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<Usuario, UsuarioResponseDTO>() {
            @Override
            protected void configure() {
                map().setCargo(cargo);
            };

        };

        TypeMap<UsuarioResponseDTO, Usuario> typeMap = BeanMapper.mapper().createTypeMap(UsuarioResponseDTO.class,
                Usuario.class);
        typeMap.addMapping(UsuarioResponseDTO::getCargo, Usuario::setCargo);
        new BeanMapper();
        return BeanMapper.mapper().map(usuario, UsuarioResponseDTO.class);
    }

    public static List<UsuarioResponseDTO> toListDTO(List<Usuario> usuarios){
        return usuarios.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }
}
