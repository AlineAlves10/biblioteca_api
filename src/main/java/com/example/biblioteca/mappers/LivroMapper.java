package com.example.biblioteca.mappers;


import com.example.biblioteca.dtos.livroDtos.LivroRequest;
import com.example.biblioteca.dtos.livroDtos.LivroResponse;
import com.example.biblioteca.entities.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    @Mapping(source = "autorId", target = "autor.id")
    Livro toEntity(LivroRequest request);

    @Mapping(source = "autor.id", target = "autorId")
    LivroResponse toResponse(Livro livro);
}
