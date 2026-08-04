package com.example.biblioteca.mappers;

import com.example.biblioteca.dtos.emprestimoDtos.EmprestimoRequest;
import com.example.biblioteca.dtos.emprestimoDtos.EmprestimoResponse;
import com.example.biblioteca.entities.Emprestimo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    Emprestimo toEntity(EmprestimoRequest request);

    EmprestimoResponse toResponse(Emprestimo emprestimo);
}
