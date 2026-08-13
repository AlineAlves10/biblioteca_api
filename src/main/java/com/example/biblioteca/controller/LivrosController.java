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
    public ResponseEntity<List<LivroResponse>> buscarLivros(
            @RequestParam(required = false) Boolean disponivel) {

        List<Livro> livros;

        if (disponivel != null && disponivel) {
            livros = service.buscarSeDisponivel();
        } else {
            livros = service.buscarTodos();
        }

        return ResponseEntity.ok(mapper.toResponseList(livros));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarLivro(@PathVariable Integer id){
        Livro livro = service.buscarPorId(id);

        return ResponseEntity.ok(mapper.toResponse(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> buscarPorTitulo(@RequestParam String titulo){
        var livro = service.buscarLivroPorTitulo(titulo);

        return ResponseEntity.ok(mapper.toResponseList(livro));

    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> buscarPorAno(@RequestParam Integer ano){
        var anu = service.buscarPorAno(ano);
        return ResponseEntity.ok(mapper.toResponseList(anu));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> buscarPorAutor(@RequestParam String nomeAutor){
        var nome = service.buscarPorAutor(nomeAutor);
        return ResponseEntity.ok(mapper.toResponseList(nome));
    }

}
