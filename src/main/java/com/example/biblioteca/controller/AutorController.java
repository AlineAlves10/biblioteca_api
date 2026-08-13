package com.example.biblioteca.controller;

import com.example.biblioteca.dtos.autorDtos.AutorRequest;
import com.example.biblioteca.dtos.autorDtos.AutorResponse;
import com.example.biblioteca.entities.Autor;
import com.example.biblioteca.mappers.AutorMapper;
import com.example.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarAutor(@RequestBody AutorRequest autor){
        final var resquest = mapper.toEntity(autor);
         service.salvarAutor(resquest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> atualizarNacionalidade(@RequestParam String nacionalidade,
                                                                @PathVariable Integer id) {

        Autor autorAtualizado = service.atualizar(nacionalidade, id);

        AutorResponse response = mapper.toResponse(autorAtualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Integer id){
           service.deletarAutor(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> buscarAutor(@PathVariable Integer id){
        var autor = service.verAutor(id);

        return ResponseEntity.ok(mapper.toResponse(autor));

    }
}
