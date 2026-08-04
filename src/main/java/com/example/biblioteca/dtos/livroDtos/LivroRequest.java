package com.example.biblioteca.dtos.livroDtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroRequest(@NotBlank String titulo,
                           @NotNull @Size(min = 1, max = 2026) Integer anoPublicacao,
                           @NotNull @Min(0) Integer quantidade) {
}
