package com.example.biblioteca.mappers;

import com.example.biblioteca.dtos.usuarioDtos.UsuarioRequest;
import com.example.biblioteca.dtos.usuarioDtos.UsuarioResponse;
import com.example.biblioteca.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequest request);

    UsuarioResponse toDto(Usuario usuario);
}
