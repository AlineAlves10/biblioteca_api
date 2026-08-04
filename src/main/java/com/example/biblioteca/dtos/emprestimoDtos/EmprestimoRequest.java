package com.example.biblioteca.dtos.emprestimoDtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EmprestimoRequest(@NotNull @PastOrPresent LocalDate dataEmprestimo,
                                @NotNull @Future LocalDate dataDevolucao,
                                @NotNull @Future LocalDate dataDevolucaoPrevista,
                                @NotBlank String status) {
}
