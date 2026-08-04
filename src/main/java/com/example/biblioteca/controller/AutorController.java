package com.example.biblioteca.controller;

import com.example.biblioteca.entities.Autor;
import com.example.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/v1/Autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;

    @PostMapping
    public ResponseEntity<Void> salvarAutor(@RequestBody Autor Autor){
        service.salvarAutor(Autor);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("/{id}")
    @PutMapping
    public ResponseEntity<Autor> atualizarAutor(@RequestBody Autor Autor,
                                               @PathVariable Integer id){
        Autor AutorAtualizado = service.atualizar(Autor, id);

        return ResponseEntity.ok().body(AutorAtualizado);
    }

    @RequestMapping("/{id}")
    @DeleteMapping
    public ResponseEntity<Void> deletarAutor(@PathVariable Integer id){
           service.deletarAutor(id);

        return new ResponseEntity<>(OK);
    }

    @RequestMapping("/{id}")
    @GetMapping
    public ResponseEntity<Optional<Autor>> buscarAutor(@PathVariable Integer id){
        Optional<Autor> Autor = service.verAutor(id);

        return ResponseEntity.ok().body(Autor);
    }
}
