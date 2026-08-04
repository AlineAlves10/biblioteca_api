package com.example.biblioteca.dtos.autorDtos;

import com.example.biblioteca.dtos.livroDtos.LivroResponse;

import java.util.List;

public record AutorResponse(String nome,
                            String nacionalidade,
                            List<LivroResponse> livros) {
}
