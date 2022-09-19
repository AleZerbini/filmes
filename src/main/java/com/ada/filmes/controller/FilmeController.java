package com.ada.filmes.controller;

import com.ada.filmes.domain.Filme;
import com.ada.filmes.service.FilmeService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
@AllArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    @PostMapping
    public ResponseEntity<Filme> adicionaFilme(@RequestBody Filme filme) {
        filmeService.adicionaFilme(filme);
        return new ResponseEntity<>(filme, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscaRemedioPorId(@PathVariable Long id) {
        Filme filme = filmeService.buscaPorId(id);
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Filme> atualizaFilme(@RequestBody Filme novoFilme) {
        Filme filme = filmeService.atualizaFilme(novoFilme);
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listarFilmes() {
        List<Filme> listaFilmes = filmeService.listarFilmes();
        return new ResponseEntity<>(listaFilmes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Filme> removerFilme(@PathVariable Long id) {
        Filme filme = filmeService.removeFilme(id);
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }
}
