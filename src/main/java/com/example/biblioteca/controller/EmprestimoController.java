package com.example.biblioteca.controller;

import com.example.biblioteca.dtos.emprestimoDtos.EmprestimoRequest;
import com.example.biblioteca.dtos.emprestimoDtos.EmprestimoResponse;
import com.example.biblioteca.entities.Emprestimo;
import com.example.biblioteca.mappers.EmprestimoMapper;
import com.example.biblioteca.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/emprestimos")
@RequiredArgsConstructor
@Tag(name = "Emprestimo", description = "Gerenciador de Emprestimo")
public class EmprestimoController {

    private final EmprestimoService service;
    private final EmprestimoMapper mapper;

    @PostMapping
    @Operation(summary = "Realizar empréstimo", description = "Realiza um novo empréstimo informando o livro, o usuário e a data prevista para devolução.")
    public ResponseEntity<EmprestimoResponse> realizarEmprestimo(
            @RequestParam Integer livroId,
            @RequestParam Integer usuarioId,
            @RequestParam LocalDate dataDevolucaoPrevista) {

        service.realizarEmprestimo(livroId, usuarioId, dataDevolucaoPrevista);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar empréstimo", description = "Atualiza os dados de um empréstimo existente a partir do seu ID.")
    public ResponseEntity<EmprestimoResponse> atualizarEmprestimo(
            @RequestBody EmprestimoRequest emprestimo,
            @PathVariable Integer id) {

        final var request = mapper.toEntity(emprestimo);
        Emprestimo emprestimoAtualizado = service.atualizarEmprestimo(request, id);

        final var response = mapper.toResponse(emprestimoAtualizado);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir empréstimo", description = "Remove um empréstimo do sistema pelo seu ID.")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Integer id) {
        service.deletarEmprestimo(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empréstimo por ID", description = "Busca um empréstimo específico pelo seu ID. Caso o empréstimo não seja encontrado, retorna 404.")
    public ResponseEntity<EmprestimoResponse> buscarEmprestimo(@PathVariable Integer id) {
        return service.verEmprestimo(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
