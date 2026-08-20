package com.example.biblioteca.controller;

import com.example.biblioteca.dtos.PageResponse;
import com.example.biblioteca.dtos.livroDtos.LivroRequest;
import com.example.biblioteca.dtos.livroDtos.LivroResponse;
import com.example.biblioteca.entities.Livro;
import com.example.biblioteca.mappers.LivroMapper;
import com.example.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/livros")
@RequiredArgsConstructor
@Tag(name = "Livros", description = "Gerenciador de Livros")
public class LivrosController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    @Operation(summary = "Cadastrar livro", description = "Cadastra um novo livro no sistema.")
    public ResponseEntity<Void> salvarLivro(@Valid @RequestBody LivroRequest livro){
        final var livri = mapper.toEntity(livro);
        service.salvarLivro(livri);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar livro", description = "Atualiza os dados de um livro existente a partir do seu ID.")
    public ResponseEntity<LivroResponse> atualizarLivro(@Valid @RequestBody LivroRequest livro,
                                                        @PathVariable Integer id){

        final var livri = mapper.toEntity(livro);
        Livro livroAtualizado = service.atualizarLivro(id, livri);

        final var resposta = mapper.toResponse(livroAtualizado);

        return ResponseEntity.ok().body(resposta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir livro", description = "Remove um livro do sistema pelo seu ID.")
    public ResponseEntity<Void> deletarLivro(@PathVariable Integer id){
        service.deletarLivro(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar livros", description = "Retorna uma lista paginada de livros. Permite filtrar por disponibilidade, ano, nome do autor e título.")
    public ResponseEntity<PageResponse<LivroResponse>> buscarLivros(Pageable page,
                                                                    @RequestParam(required = false) Boolean disponivel,
                                                                    @RequestParam(required = false) Integer ano,
                                                                    @RequestParam(required = false) String nomeAutor,
                                                                    @RequestParam(required = false) String titulo) {

        Page<Livro> livros;

        if (disponivel != null && disponivel) {
            livros = service.buscarSeDisponivel(page);
        } else {
            livros = service.buscarTodos(page, nomeAutor, ano, titulo);
        }

        Page<LivroResponse> responsePage =
                livros.map(mapper::toResponse);

        PageResponse<LivroResponse> response = new PageResponse<>(
                responsePage.getNumber(),
                responsePage.getSize(),
                responsePage.getTotalElements(),
                responsePage.getTotalPages(),
                responsePage.getContent()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Busca um livro específico pelo seu ID.")
    public ResponseEntity<LivroResponse> buscarLivro(@PathVariable Integer id){
        Livro livro = service.buscarPorId(id);

        return ResponseEntity.ok(mapper.toResponse(livro));
    }
}
