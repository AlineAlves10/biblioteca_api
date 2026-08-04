package com.example.biblioteca.dtos.autorDtos;

import jakarta.validation.constraints.NotBlank;

public record AutorRequest(@NotBlank String nome,
                           @NotBlank String nacionalidade) {
}
