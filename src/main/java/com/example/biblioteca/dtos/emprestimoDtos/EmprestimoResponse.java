package com.example.biblioteca.dtos.emprestimoDtos;

import java.util.Date;

public record EmprestimoResponse(Date dataEmprestimo,
                                 Date dataDevolucao,
                                 Date dataDevolucaoPrevista,
                                 String status) {
}
