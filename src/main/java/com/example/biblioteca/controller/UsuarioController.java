package com.example.biblioteca.controller;

import com.example.biblioteca.entities.Usuario;
import com.example.biblioteca.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario Usuario){
        service.salvarUsuario(Usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("/{id}")
    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario,
                                               @PathVariable Integer id){
        Usuario UsuarioAtualizado = service.atualizarUsuario(usuario, id);

        return ResponseEntity.ok().body(UsuarioAtualizado);
    }

    @RequestMapping("/{id}")
    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id){
           service.deletarUsuario(id);

        return new ResponseEntity<>(OK);
    }

    @RequestMapping("/{id}")
    @GetMapping
    public ResponseEntity<Optional<Usuario>> buscarUsuario(@PathVariable Integer id){
        Optional<Usuario> Usuario = service.verUsuario(id);

        return ResponseEntity.ok().body(Usuario);
    }
}
