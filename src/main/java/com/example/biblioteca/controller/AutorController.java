package com.example.biblioteca.controller;

import com.example.biblioteca.dtos.autorDtos.AutorRequest;
import com.example.biblioteca.dtos.autorDtos.AutorResponse;
import com.example.biblioteca.entities.Autor;
import com.example.biblioteca.mappers.AutorMapper;
import com.example.biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/autores")
@RequiredArgsConstructor
@Tag(name = "Autor", description = "Gerenciador de Autor")
public class AutorController {

    private final AutorService service;
    private final AutorMapper mapper;

    @PostMapping
    @Operation(summary = "Cadastrar autor", description = "Cadastra um novo autor no sistema.")
    public ResponseEntity<Void> salvarAutor(@RequestBody AutorRequest autor) {
        final var request = mapper.toEntity(autor);
        service.salvarAutor(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar nacionalidade do autor", description = "Atualiza a nacionalidade de um autor existente pelo seu ID.")
    public ResponseEntity<AutorResponse> atualizarNacionalidade(
            @RequestParam String nacionalidade,
            @PathVariable Integer id) {

        Autor autorAtualizado = service.atualizar(nacionalidade, id);

        AutorResponse response = mapper.toResponse(autorAtualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir autor", description = "Remove um autor do sistema pelo seu ID.")
    public ResponseEntity<Void> deletarAutor(@PathVariable Integer id) {
        service.deletarAutor(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor por ID", description = "Retorna os dados de um autor específico a partir do seu ID.")
    public ResponseEntity<AutorResponse> buscarAutor(@PathVariable Integer id) {
        var autor = service.verAutor(id);

        return ResponseEntity.ok(mapper.toResponse(autor));
    }
}
