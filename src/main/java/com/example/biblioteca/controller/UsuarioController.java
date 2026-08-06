package com.example.biblioteca.controller;

import com.example.biblioteca.dtos.usuarioDtos.UsuarioRequest;
import com.example.biblioteca.dtos.usuarioDtos.UsuarioResponse;
import com.example.biblioteca.entities.Usuario;
import com.example.biblioteca.mappers.UsuarioMapper;
import com.example.biblioteca.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody UsuarioRequest usuario){
        final var request = mapper.toEntity(usuario);
        service.salvarUsuario(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@RequestParam String email,
                                                            @PathVariable Integer id){

        Usuario usuarioAtualizado = service.atualizarUsuario(email, id);

        final var response = mapper.toDto(usuarioAtualizado);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id){
           service.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@PathVariable Integer id){
        return service.verUsuario(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
