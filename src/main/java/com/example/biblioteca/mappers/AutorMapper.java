package com.example.biblioteca.mappers;

import com.example.biblioteca.dtos.autorDtos.AutorRequest;
import com.example.biblioteca.dtos.autorDtos.AutorResponse;
import com.example.biblioteca.entities.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorRequest request);

    AutorResponse toResponse(Autor autor);
}
