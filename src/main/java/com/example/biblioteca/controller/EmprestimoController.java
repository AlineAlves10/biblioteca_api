package com.example.biblioteca.controller;

import com.example.biblioteca.entities.Emprestimo;
import com.example.biblioteca.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/v1/Emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService service;

    @PostMapping
    public ResponseEntity<Void> salvarEmprestimo(@RequestBody Emprestimo Emprestimo){
        service.salvarEmprestimo(Emprestimo);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("/{id}")
    @PutMapping
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@RequestBody Emprestimo emprestimo,
                                               @PathVariable Integer id){
        Emprestimo EmprestimoAtualizado = service.atualizarEmprestimo(emprestimo, id);

        return ResponseEntity.ok().body(EmprestimoAtualizado);
    }

    @RequestMapping("/{id}")
    @DeleteMapping
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Integer id){
           service.deletarEmprestimo(id);

        return new ResponseEntity<>(OK);
    }


    @RequestMapping("/{id}")
    @GetMapping
    public ResponseEntity<Optional<Emprestimo>> buscarEmprestimo(@PathVariable Integer id){
        Optional<Emprestimo> Emprestimo = service.verEmprestimo(id);

        return ResponseEntity.ok().body(Emprestimo);
    }
}
