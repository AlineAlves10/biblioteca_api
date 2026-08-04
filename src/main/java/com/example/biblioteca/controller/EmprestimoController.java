package com.example.biblioteca.controller;

import com.example.biblioteca.dtos.emprestimoDtos.EmprestimoRequest;
import com.example.biblioteca.dtos.emprestimoDtos.EmprestimoResponse;
import com.example.biblioteca.entities.Emprestimo;
import com.example.biblioteca.mappers.EmprestimoMapper;
import com.example.biblioteca.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService service;
    private final EmprestimoMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarEmprestimo(@RequestBody EmprestimoRequest emprestimo){

        final var request = mapper.toEntity(emprestimo);
        service.salvarEmprestimo(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoResponse> atualizarEmprestimo(@RequestBody EmprestimoRequest emprestimo,
                                               @PathVariable Integer id){
        final var request = mapper.toEntity(emprestimo);
        Emprestimo emprestimoAtualizado = service.atualizarEmprestimo(request, id);

        final var response = mapper.toResponse(emprestimoAtualizado);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Integer id){
           service.deletarEmprestimo(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponse> buscarEmprestimo(@PathVariable Integer id){
        return service.verEmprestimo(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
