package com.example.biblioteca.controller;

import com.example.biblioteca.dtos.livroDtos.LivroRequest;
import com.example.biblioteca.dtos.livroDtos.LivroResponse;
import com.example.biblioteca.entities.Livro;
import com.example.biblioteca.mappers.LivroMapper;
import com.example.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/livros")
@RequiredArgsConstructor
public class LivrosController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarLivro(@Valid @RequestBody LivroRequest livro){
        final var livri = mapper.toEntity(livro);
        service.salvarLivro(livri);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizarLivro(@Valid @RequestBody LivroRequest livro,
                                                        @PathVariable Integer id){
        final var livri = mapper.toEntity(livro);
        Livro livroAtualizado = service.atualizarLivro(id, livri);

        final var resposta = mapper.toResponse(livroAtualizado);

        return ResponseEntity.ok().body(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Integer id){
           service.deletarLivro(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> buscarLivros(){
        List<Livro> livro = service.buscarTodos();

        final var resposta = mapper.toResponseList(livro);

        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarLivro(@PathVariable Integer id){
        return service.buscarPorId(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
